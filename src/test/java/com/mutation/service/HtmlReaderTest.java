package com.mutation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.File;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.mutation.html.HtmlReader;
import com.mutation.model.PiTestCoverageReport;

class HtmlReaderTest {

	@Test
	void readHtmlFileTest() {
		String projectFolderName = "StudentJUnitAssignment";
		String currentDirectory = System.getProperty("user.dir");
		String testWorkspaceDirectory = currentDirectory  + File.separator + "src\\test\\resources\\workspace";
		HtmlReader htmlReader = new HtmlReader(projectFolderName);
		PiTestCoverageReport piTestCoverageReport = htmlReader.readHtmlFile(testWorkspaceDirectory);
		assertNotEquals(null, piTestCoverageReport);
		assertEquals(BigDecimal.valueOf(68.0), piTestCoverageReport.getMutationCoveragePercentage());
	}
}
