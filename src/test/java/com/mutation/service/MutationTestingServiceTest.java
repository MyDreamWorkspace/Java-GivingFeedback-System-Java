package com.mutation.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MutationTestingServiceTest {

	@BeforeEach
	void setUp(){
		
				
	}
	
	@Test
	void copyInputProjectToTempFolderTest() {
		
		String projectFolderName = "StudentJUnitAssignment";
		MutationTestingService mutationTestingService = new MutationTestingService(projectFolderName);
		
		String currentDirectory = System.getProperty("user.dir");
		String testWorkspaceDirectory = currentDirectory  + File.separator + "src\\test\\resources\\workspace";
		try {
			mutationTestingService.copyInputProjectToTempFolder(testWorkspaceDirectory);
			
			String tempDirectory = testWorkspaceDirectory + File.separator + "temp";
			
			File directory = new File(tempDirectory + File.separator + projectFolderName);
			
			assertTrue(directory.exists());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void copyInputProjectToTempFolderZipTest() {
		
		String projectFolderName = "BankingApplicationSystem";
		MutationTestingService mutationTestingService = new MutationTestingService(projectFolderName);
		
		String currentDirectory = System.getProperty("user.dir");
		String testWorkspaceDirectory = currentDirectory  + File.separator + "src\\test\\resources\\workspace";
		try {
			mutationTestingService.copyInputProjectToTempFolder(testWorkspaceDirectory);
			
			String tempDirectory = testWorkspaceDirectory + File.separator + "temp";
			
			File directory = new File(tempDirectory + File.separator + projectFolderName);
			
			assertTrue(directory.exists());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void createCommandsForUnitTestingTest() {
		String projectFolderName = "StudentJUnitAssignment";
		MutationTestingService mutationTestingService = new MutationTestingService(projectFolderName);
		
		String currentDirectory = System.getProperty("user.dir");
		String testWorkspaceDirectory = currentDirectory  + File.separator + "src\\test\\resources\\workspace";
		try {
			mutationTestingService.createCommandsForUnitTesting(testWorkspaceDirectory);
			File file = new File(testWorkspaceDirectory + File.separator + "mvn-junit-test.bat");
			assertTrue(file.exists());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void createCommandsForMutationTestingTest() {
		String projectFolderName = "StudentJUnitAssignment";
		MutationTestingService mutationTestingService = new MutationTestingService(projectFolderName);
		
		String currentDirectory = System.getProperty("user.dir");
		String testWorkspaceDirectory = currentDirectory  + File.separator + "src\\test\\resources\\workspace";
		try {
			mutationTestingService.createCommandsForMutationTesting(testWorkspaceDirectory);
			File file = new File(testWorkspaceDirectory + File.separator + "mvn-pitest.bat");
			assertTrue(file.exists());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
