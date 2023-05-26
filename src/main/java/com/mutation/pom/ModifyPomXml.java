package com.mutation.pom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import com.mutation.exception.InvalidProjectException;

/**
 * Class responsible for modify pom.xml file assigment project in temp folder
 *
 */
public class ModifyPomXml {

	private static final String INPUT_PROJECT_DIRECTORY = "student-assignment-solution/";
	private static final String POM_XML_FILE_NAME = "pom.xml";
	private static final String TEMPORARY_PROJECT_DIRECTORY = "temp/";

	private static final String PITEST_GROUP_ID = "org.pitest";

	private final String projectFolderName;
	private final String currentDirectory;

	public ModifyPomXml(String currentDirectory, String projectFolderName) {
		
		this.currentDirectory = currentDirectory;
		this.projectFolderName = projectFolderName;
	}

	/**
	 * write modified @Model to pom.xml
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @throws InvalidProjectException
	 */
	public void writeModifiedModelToFile() throws IOException, XmlPullParserException, InvalidProjectException {
		
		MavenXpp3Writer writer = new MavenXpp3Writer();
		
		File file = new File(currentDirectory + File.separator + TEMPORARY_PROJECT_DIRECTORY + File.separator + projectFolderName + File.separator
				+ POM_XML_FILE_NAME);
		
		Model model = populateModifiedModel();
		
		try (FileWriter fileWriter = new FileWriter(file)) {
			
			writer.write(fileWriter, model);
		}

	}

	/**
	 * @return modified @Model of pom.xml
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @throws InvalidProjectException
	 */
	public Model populateModifiedModel() throws IOException, XmlPullParserException, InvalidProjectException {

		Model model = populateModel();
		addPiTestDependency(model);
		addPlugin(model);
		return model;

	}

	/**
	 * Read pom.xml of selected project
	 * @return @Model type of pom.xml
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @throws InvalidProjectException
	 */
	public Model populateModel() throws IOException, XmlPullParserException, InvalidProjectException {
		
		MavenXpp3Reader reader = new MavenXpp3Reader();
		
		try {
			
		FileReader fileReader = new FileReader(currentDirectory + File.separator + INPUT_PROJECT_DIRECTORY + File.separator + projectFolderName
				+ File.separator + POM_XML_FILE_NAME);

		return reader.read(fileReader);
		
		}
		
		catch(FileNotFoundException e) {
			
			throw new InvalidProjectException("Given project is invalid, pom.xml file not found in the project.");
		}
	}

	/**
	 * Add PiTest dependency into given pom.xml model
	 * @param model of type @Model
	 * @throws InvalidProjectException
	 */
	public void addPiTestDependency(Model model) throws InvalidProjectException {
		
		boolean isDependencyPresent = false;
		
		if(model.getDependencies() == null || model.getDependencies().isEmpty()) {
			
			throw new InvalidProjectException("Given project is invalid, pom.xml file does not contain any dependency.\nAtleast junit dependency is expected.");
		}
		
		for (Dependency dependency : model.getDependencies()) {
			
			if (dependency.getArtifactId().equalsIgnoreCase("pitest-parent")) {
				
				isDependencyPresent = true;
				
				break;
			}
		}

		if (!isDependencyPresent) {
			
			Dependency dependency = new Dependency();
			
			dependency.setGroupId(PITEST_GROUP_ID);
			dependency.setArtifactId("pitest-parent");
			dependency.setVersion("1.9.0");
			dependency.setType("pom");

			model.addDependency(dependency);
		}
	}

	/**
	 * Add plugin pitest-maven into given pom.xml model
	 * @param model of type @Model
	 * @throws InvalidProjectException
	 */
	public void addPlugin(Model model) throws InvalidProjectException {
		
		boolean isPluginPresent = false;
		
		if(model.getBuild() == null) {
			
			throw new InvalidProjectException("Given project is invalid, pom.xml file does not contain build tag and plugins.\nAtleast maven-surefire-plugin and maven-compiler-plugin expected.");
		}
		
		if(model.getBuild().getPlugins() == null || model.getBuild().getPlugins().isEmpty()) {
			
			throw new InvalidProjectException("Given project is invalid, pom.xml file does not contain any plugins.\nAtleast maven-surefire-plugin and maven-compiler-plugin expected.");
		}
		
		for (Plugin plugin : model.getBuild().getPlugins()) {
			
			if (plugin.getArtifactId().equalsIgnoreCase("pitest-maven")) {
				
				isPluginPresent = true;
				
				break;
			}
		}

		if (!isPluginPresent) {
			
			Plugin plugin = new Plugin();
			plugin.setGroupId(PITEST_GROUP_ID);
			plugin.setArtifactId("pitest-maven");
			plugin.setVersion("1.9.0");

			Dependency pluginDependency = new Dependency();
			pluginDependency.setGroupId(PITEST_GROUP_ID);
			pluginDependency.setArtifactId("pitest-junit5-plugin");
			pluginDependency.setVersion("1.0.0");

			plugin.addDependency(pluginDependency);

			Xpp3Dom configuration = new Xpp3Dom("configuration");
			Xpp3Dom targetClasses = new Xpp3Dom("targetClasses");
			Xpp3Dom targetClassesParam = new Xpp3Dom("param");
			
			targetClassesParam.setValue("com.*");
			targetClasses.addChild(targetClassesParam);
			configuration.addChild(targetClasses);

			Xpp3Dom targetTests = new Xpp3Dom("targetTests");
			Xpp3Dom targetTestsParam = new Xpp3Dom("param");
			
			targetTestsParam.setValue("*");
			targetTests.addChild(targetTestsParam);
			configuration.addChild(targetTests);

			plugin.setConfiguration(configuration);
			
			model.getBuild().addPlugin(plugin);
		}

	}
}
