package com.mutation.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import com.mutation.exception.InvalidProjectException;
import com.mutation.pom.ModifyPomXml;

import net.lingala.zip4j.ZipFile;

/**
 * Class responsible to prepare selected project to run pitest and perform pitest
 *
 */
public class MutationTestingService {

	private static final String MAVEN_FOLDER = "apache-maven-3.8.6";
	private static final String CURRENT_DIRECTORY = "user.dir";

	private String projectFolderName;
	
	private static final Logger logger = LogManager.getLogger(MutationTestingService.class);  

	public MutationTestingService(String projectFolderName) {
		
		this.projectFolderName = projectFolderName;
	}

	/**
	 * @return true if selected project setup is successful, otherwise false
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @throws InvalidProjectException
	 */
	public boolean setupSelectedProjectAndPerformTesting() throws IOException, XmlPullParserException, InvalidProjectException {
		
			String currentDirectory = System.getProperty(CURRENT_DIRECTORY);
			
			copyInputProjectToTempFolder(currentDirectory);
			
			includePiTestDependencyInPomFile(currentDirectory);

			createCommandsForUnitTesting(currentDirectory);
			
			if(!performJUnitTestingUsingProcessBuilder()) {
				
				System.out.println("One or more JUnit test cases failed!.");
				
				return false;
			}

			createCommandsForMutationTesting(currentDirectory);
			
			performMutationTestingUsingProcessBuilder();
			
			return true;
		
	}

	/**
	 * Copy selected project from student-assignment-solution folder to temp folder
	 * @param currentDirectory
	 * @throws IOException
	 */
	public void copyInputProjectToTempFolder(String currentDirectory) throws IOException {
		
		String sourceDirectory = currentDirectory + File.separator + "student-assignment-solution" + File.separator + projectFolderName;
		String targetDirectory = currentDirectory + File.separator + "temp";

		File targetDirectoryFile =  new File(targetDirectory);
		
		if(targetDirectoryFile.exists()) {
			
			FileUtils.forceDelete(targetDirectoryFile);			
		}
		
		targetDirectoryFile.mkdir();
		
		File sourceDirectoryFile = new File(sourceDirectory);
		
		if(sourceDirectoryFile.isDirectory()) {
			
			FileUtils.copyDirectoryToDirectory(new File(sourceDirectory), new File(targetDirectory));
		}
		
		if(sourceDirectoryFile.isFile() && sourceDirectoryFile.toString().endsWith(".zip")) {
			
			try(ZipFile zipFile = new ZipFile(sourceDirectoryFile)){
				
				projectFolderName = projectFolderName.substring(0, projectFolderName.indexOf(".zip"));	
				
				zipFile.extractAll(targetDirectory);				
			}
		}
	}

	/**
	 * Add pitest dependency to pom.xml of selected project
	 * @param currentDirectory
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @throws InvalidProjectException
	 */
	public void includePiTestDependencyInPomFile(String currentDirectory) throws IOException, XmlPullParserException, InvalidProjectException {
		
		ModifyPomXml modifyPomXml = new ModifyPomXml(currentDirectory, projectFolderName);
		
		modifyPomXml.writeModifiedModelToFile();
	}

	/**
	 * Create batch commands to run junit test cases
	 * @param currentDirectory
	 * @throws IOException
	 */
	public void createCommandsForUnitTesting(String currentDirectory) throws IOException {

		String maven = currentDirectory + File.separator + MAVEN_FOLDER + File.separator + "bin" + File.separator
				+ "mvn";

		String outputProjectPath = currentDirectory + File.separator + "temp" + File.separator + projectFolderName;
		String commandToTempProjectFolder = "cd " + outputProjectPath;
		String commandToRunTest = "call " + maven + " test";
		
		List<String> lines = Arrays.asList(commandToTempProjectFolder, commandToRunTest);
		
		writeCommandToFile(lines, currentDirectory + File.separator + "mvn-junit-test.bat");
	}

	/**
	 * Create batch commands to run mutation testing
	 * @param currentDirectory
	 * @throws IOException
	 */
	public void createCommandsForMutationTesting(String currentDirectory) throws IOException {
		
		String maven = currentDirectory + File.separator + MAVEN_FOLDER + File.separator + "bin" + File.separator
				+ "mvn";

		String outputProjectPath = currentDirectory + File.separator + "temp" + File.separator + projectFolderName;
		String commandToTempProjectFolder = "cd " + outputProjectPath;
		String commandToRunPiTest = "call " + maven + " org.pitest:pitest-maven:mutationCoverage";
		
		List<String> lines = Arrays.asList(commandToTempProjectFolder, commandToRunPiTest);
		
		writeCommandToFile(lines, currentDirectory + File.separator + "mvn-pitest.bat");
	}

	/**
	 * @return true if execution successful, otherwise return false
	 * @throws IOException
	 */
	private boolean performJUnitTestingUsingProcessBuilder() throws IOException {

		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c mvn-junit-test.bat");
		
		builder.redirectErrorStream(true);

		try {
			
			Process process = builder.start();
			BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			while (true) {
				
				String line = r.readLine();

				if (line == null) {
					break;
				}
				System.out.println(line);
				
				if(line.contains("BUILD FAILURE")) {
					return false;
				}
			}
		} 
		catch (IOException e) {
			
			logger.error(e.getMessage());
			
			return false;
		}
		return true;
	}

	/**
	 * Perform mutation testing using @ProcessBuilder
	 * @throws IOException
	 */
	private void performMutationTestingUsingProcessBuilder() throws IOException {

		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c mvn-pitest.bat");
		
		builder.redirectErrorStream(true);

		try {
			Process process = builder.start();
			BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			while (true) {
				String line = r.readLine();

				if (line == null) {
					break;
				}
				System.out.println(line);
			}
		} 
		catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * Write command lines to file
	 * @param lines of type @List<String>
	 * @param filePathStr of type @String
	 * @throws IOException
	 */
	private void writeCommandToFile(List<String> lines, String filePathStr) throws IOException {
		// Defining the file name of the file
		Path filePath = Path.of(filePathStr);

		// Writing into the file
		Files.write(filePath, lines, StandardCharsets.UTF_8);
	}
}
