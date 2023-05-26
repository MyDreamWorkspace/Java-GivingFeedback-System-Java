package com.mutation.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.junit.jupiter.api.Test;

import com.mutation.exception.InvalidProjectException;
import com.mutation.pom.ModifyPomXml;

class ModifyPomXmlTest {

	@Test
	void addPiTestDependencyTest() {
		String projectFolderName = "StudentJUnitAssignment";
		String currentDirectory = System.getProperty("user.dir");
		String testWorkspaceDirectory = currentDirectory  + File.separator + "src\\test\\resources\\workspace";
		try {
			MutationTestingService mutationTestingService = new MutationTestingService(projectFolderName);
			mutationTestingService.copyInputProjectToTempFolder(testWorkspaceDirectory);
			ModifyPomXml modifyPomXml = new ModifyPomXml(testWorkspaceDirectory, projectFolderName);
			Model model = modifyPomXml.populateModel();
			modifyPomXml.addPiTestDependency(model);
			
			boolean isDependencyPresent = false;
			for (Dependency dependency : model.getDependencies()) {
				if (dependency.getArtifactId().equalsIgnoreCase("pitest-parent")) {
					isDependencyPresent = true;
					break;
				}
			}
			assertTrue(isDependencyPresent);
		} catch (IOException | XmlPullParserException | InvalidProjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void addPluginTest() {
		String projectFolderName = "StudentJUnitAssignment";
		String currentDirectory = System.getProperty("user.dir");
		String testWorkspaceDirectory = currentDirectory  + File.separator + "src\\test\\resources\\workspace";
		try {
			MutationTestingService mutationTestingService = new MutationTestingService(projectFolderName);
			mutationTestingService.copyInputProjectToTempFolder(testWorkspaceDirectory);
			ModifyPomXml modifyPomXml = new ModifyPomXml(testWorkspaceDirectory, projectFolderName);
			Model model = modifyPomXml.populateModel();
			modifyPomXml.addPlugin(model);
			
			boolean isPluginPresent = false;
			for (Plugin plugin : model.getBuild().getPlugins()) {
				if (plugin.getArtifactId().equalsIgnoreCase("pitest-maven")) {
					isPluginPresent = true;
					break;
				}
			}
			assertTrue(isPluginPresent);
		} catch (IOException | XmlPullParserException | InvalidProjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
