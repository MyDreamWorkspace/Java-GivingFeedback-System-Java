package com.mutation.html;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mutation.model.PiTestCoverageReport;
import com.mutation.model.SurvivedModel;
import com.mutation.util.MutationOperator;
import com.mutation.util.MutationOperators;

/**
 * Class responsible to read the generated report/ html file
 *
 */
public class HtmlReader {

	private final String projectFolderName;
	private boolean printActiveMutators = true;
	private MutationOperators mutationOperators = new MutationOperators();

	private static final String MUTATION_COVERAGE_PERCENTAGE_THRESHOLD = "mutation_coverage_percentage_threshold";

	private static final Logger logger = LogManager.getLogger(HtmlReader.class);

	public HtmlReader(String projectFolderName) {
		
		this.projectFolderName = projectFolderName;
	}

	/**
	 * Print mutation score of selected project and compare with score configured in
	 * application.properties file
	 */
	public void showReport() {
		
		HtmlReader htmlReader = new HtmlReader(this.projectFolderName);
		String currentDirectory = System.getProperty("user.dir");
		PiTestCoverageReport piTestCoverageReport = htmlReader.readHtmlFile(currentDirectory);

		try (FileReader reader = new FileReader("application.properties")) {
			
			Properties properties = new Properties();
			
			properties.load(reader);
			
			BigDecimal thresholdMutationCoveragePercentage = BigDecimal
					.valueOf(Double.valueOf(properties.getProperty(MUTATION_COVERAGE_PERCENTAGE_THRESHOLD)));
			
			System.out.println("---------------------------------------------------------");
			System.out.println("SUMMARY");
			System.out.println("---------------------------------------------------------");
			
			if (piTestCoverageReport.getMutationCoveragePercentage().doubleValue() < thresholdMutationCoveragePercentage
					.doubleValue()) {
				
				System.out.println("Your mutation test score is "
						+ piTestCoverageReport.getMutationCoveragePercentage().doubleValue()
						+ " which is less than the Professor threshold "
						+ thresholdMutationCoveragePercentage.doubleValue() + ".\n"
						+ "Please try to improve your test scenarios.");
			} 
			else if (piTestCoverageReport.getMutationCoveragePercentage()
					.doubleValue() > thresholdMutationCoveragePercentage.doubleValue()) {
				System.out.println("Your mutation test score is "
						+ piTestCoverageReport.getMutationCoveragePercentage().doubleValue()
						+ " which is greater than the Professor threshold "
						+ thresholdMutationCoveragePercentage.doubleValue() + ".");
			} 
			else {
				System.out.println("Your mutation test score is "
						+ piTestCoverageReport.getMutationCoveragePercentage().doubleValue()
						+ " which is equal to the Professor threshold "
						+ thresholdMutationCoveragePercentage.doubleValue() + ".");
			}
		} 
		catch (IOException e) {
			logger.error(e.getMessage());
		}

	}

	/**
	 * Read generated html files which contains the report of mutation testing
	 * 
	 * @param currentDirectory
	 * @return object of @PiTestCoverageReport
	 */
	public PiTestCoverageReport readHtmlFile(String currentDirectory) {
		
		String outputProjectPiTestPath = currentDirectory + File.separator + "temp" + File.separator + projectFolderName
				+ File.separator + "target" + File.separator + "pit-reports";

		File file = new File(outputProjectPiTestPath);
		
		String[] arr = file.list();
		
		String folderName = arr[0];
		
		String[] files = new File(outputProjectPiTestPath + File.separator + folderName).list();
		
		boolean isTitleDisplayed = false;
		
		for (String f1 : files) {
			
			File dir = new File(outputProjectPiTestPath + File.separator + folderName + File.separator + f1);
			
			if (dir.isDirectory()) {
				
				File[] javaFiles = dir.listFiles();
				
				if (printActiveMutators && javaFiles.length > 0) {
					
					showActiveMutatorsfromHtmlFile(javaFiles[0]);
				}
				
				if(!isTitleDisplayed) {
					
					System.out.println("\nSurvived Muatation details in Java classes : ");
					
					isTitleDisplayed = true;
				}
				
				for (File javaFile : javaFiles) {
					
					readJavaHtmlFile(javaFile);
				}
			}
		}
		
		String htmlFilePath = outputProjectPiTestPath + File.separator + folderName + File.separator + "index.html";
		
		Document htmlDoc = null;
		
		try {
			
			htmlDoc = Jsoup.parse(new File(htmlFilePath));
			
		} 
		catch (IOException e) {
			
			logger.error(e.getMessage());
		}

		Elements tablesEls = htmlDoc.select("table");
		Element tableEl = tablesEls.get(0);
		Elements theadEls = tableEl.getElementsByTag("thead");
		Element theadEl = theadEls.get(0);
		Elements thEls = theadEl.getElementsByTag("th");
		
		int rowCount = 0;
		int numberofClassesRowNumber = 0;
		int lineCoverageRowNumber = 0;
		int mutationRowNumber = 0;
		int testStrengthRowNumber = 0;
		
		for (Element thEl : thEls) {
			
			String txt = thEl.text();
			
			if (txt.contains("Number of Classes")) {
				numberofClassesRowNumber = rowCount;
			}
			if (txt.contains("Line Coverage")) {
				lineCoverageRowNumber = rowCount;
			}
			if (txt.contains("Mutation Coverage")) {
				mutationRowNumber = rowCount;
			}
			if (txt.contains("Test Strength")) {
				testStrengthRowNumber = rowCount;
			}
			
			rowCount++;
		}

		Elements tbodyEls = tableEl.getElementsByTag("tbody");
		Element tbodyEl = tbodyEls.get(0);
		Elements tdEls = tbodyEl.getElementsByTag("td");

		Element numberofClassesValueEl = tdEls.get(numberofClassesRowNumber);
		String numberofClassesValueStrRaw = numberofClassesValueEl.text();
		Integer numberOfClasses = extractValue(numberofClassesValueStrRaw);

		Element lineCoverageValueEl = tdEls.get(lineCoverageRowNumber);
		String lineCoverageValueStrRaw = lineCoverageValueEl.text();
		BigDecimal lineCoverage = extractPercentageValue(lineCoverageValueStrRaw);

		Element mutationCoverageValueEl = tdEls.get(mutationRowNumber);
		String mutationCoverageValueStrRaw = mutationCoverageValueEl.text();
		BigDecimal mutationCoverage = extractPercentageValue(mutationCoverageValueStrRaw);

		Element testStrengthValueEl = tdEls.get(testStrengthRowNumber);
		String testStrengthValueStrRaw = testStrengthValueEl.text();
		BigDecimal testStrength = extractPercentageValue(testStrengthValueStrRaw);

		return new PiTestCoverageReport(numberOfClasses, lineCoverage, mutationCoverage, testStrength);

	}

	/**
	 * Extract percentage value
	 * 
	 * @param value of @String type
	 * @return percentage value of @BigDecimal type
	 */
	private BigDecimal extractPercentageValue(String value) {
		String valueStr = value.substring(0, value.indexOf("%"));
		return BigDecimal.valueOf(Double.valueOf(valueStr));
	}

	/**
	 * @param value
	 * @return value of @Integer type
	 */
	private Integer extractValue(String value) {
		return Integer.valueOf(value);
	}

	/**
	 * Read report from html file respective to java file
	 * 
	 * @param javaHtmlFile, html file of java file report
	 */
	public void readJavaHtmlFile(File javaHtmlFile) {
		
		Document htmlDoc = null;
		
		try {
			htmlDoc = Jsoup.parse(javaHtmlFile);
			
		} catch (IOException e) {
			
			logger.error(e.getMessage());
		}

		Element mutationTableRowEl = null;
		Elements tableRowEls = htmlDoc.select("tr");
		
		boolean breakLoopFlag = false;
		
		for (Element tableRowEl : tableRowEls) {
			
			for (Element tableColumnEl : tableRowEl.children()) {
				
				for (Element h2El : tableColumnEl.children()) {
					
					if (h2El.text().contentEquals("Mutations")) {
						
						mutationTableRowEl = tableRowEl;
						breakLoopFlag = true;
						break;
					}
				}
				if (breakLoopFlag) {
					break;
				}
			}
			if (breakLoopFlag) {
				break;
			}
		}

		if (mutationTableRowEl == null)
			return;
		
		Elements mutationRowEls = mutationTableRowEl.nextElementSiblings();

		List<SurvivedModel> survivedModelList = new LinkedList<>();
		
		for (Element mutationRowEl : mutationRowEls) {
			
			for (Element mutationColumnEl : mutationRowEl.children()) {
				
				SurvivedModel survivedModel = new SurvivedModel();
				Elements survivedEls = mutationColumnEl.getElementsByAttributeValue("class", "SURVIVED");
				
				if (!survivedEls.isEmpty()) {
					
					Element survivedEl = survivedEls.first();

					populateClassNamesfromHtmlFile(survivedEl, javaHtmlFile, survivedModel);
					
					Element mutationFirstColumnEl = mutationRowEl.children().first();
					
					survivedModel.setLineNumber(mutationFirstColumnEl.text());
					
					survivedModelList.add(survivedModel);
				}
			}
		}

		// Find mutated expression
		populateMutatedExpression(tableRowEls, survivedModelList);

		// Print PiTest report
		printReport(survivedModelList);

	}

	/**
	 * Populate original and mutated expression of survived java code
	 * 
	 * @param tableRowEls
	 * @param survivedModelList
	 */
	private void populateMutatedExpression(Elements tableRowEls, List<SurvivedModel> survivedModelList) {
		
		for (Element tableRowEl : tableRowEls) {
			
			for (Element tableColumnEl : tableRowEl.children()) {
				
				if (tableColumnEl.className().contentEquals("survived")) {
					
					Elements els = tableColumnEl.siblingElements();
					
					SurvivedModel survivedModel = null;
					
					Elements survivedEls = null;
					
					for (Element el : els) {
						
						if (survivedModel == null) {
							
							for (SurvivedModel survivedModelTemp : survivedModelList) {
								
								if (survivedModelTemp.getLineNumber().contentEquals(el.text())
										
										&& survivedModelTemp.getMutationOperator() != null) {
									
									survivedModel = survivedModelTemp;
									break;
								}
							}
						}
						
						if (survivedEls == null || survivedEls.isEmpty()) {
							survivedEls = el.getElementsByAttributeValue("class", "SURVIVED");
						}

						if (survivedEls.size() == 1 && survivedModel != null) {
							MutationOperator mutationOperator = mutationOperators.mutationOperatorMap
									.get(survivedModel.getMutationOperator());
							for (Entry<String, String> entry : mutationOperator.getConditions().entrySet()) {
								
								if (survivedEls.first().text().contains(entry.getKey())) {
									
									survivedModel.setOriginalExpression(entry.getKey());
									survivedModel.setMutatedExpression(entry.getValue());
									survivedModel.setLineJavaCode(survivedEls.first().text());
									
									break;
								}
							}
						}

					}
				}
			}

		}
	}

	/**
	 * Print message, package name, java class name and line number
	 * 
	 * @param survivedModelList
	 */
	private void printReport(List<SurvivedModel> survivedModelList) {
		
		for (SurvivedModel survivedModel : survivedModelList) {
			
			if (survivedModel.getOriginalExpression() != null && survivedModel.getMutatedExpression() != null
					&& survivedModel.getLineJavaCode() != null) {
				
				System.out.println("\n----------------");
				System.out.println("\nTest cases not detected when '"+ survivedModel.getOriginalExpression()
				+ "' replaced by '" + survivedModel.getMutatedExpression() + "'\nin java code '"
				+ survivedModel.getLineJavaCode() + "'.\nPlease improve test cases.");
				System.out.println("\nPackage Name : " + survivedModel.getPackageName());
				System.out.println("Java Class Name : " + survivedModel.getClassName());
				System.out.println("Line : " + survivedModel.getLineNumber());
			}
		}
	}

	/**
	 * Populate class name and package name of html file
	 * 
	 * @param survivedModel
	 * @param survivedEl,   html tag element of survived
	 * @param htmlFile,     html file of java file report
	 */
	private void populateClassNamesfromHtmlFile(Element survivedEl, File htmlFile, SurvivedModel survivedModel) {
		
		String filePath = htmlFile.toString();
		// System.out.println("----------------");
		String javaFileName = filePath.substring(filePath.lastIndexOf('\\') + 1, filePath.lastIndexOf('.'));
		String tempStr = filePath.substring(0, filePath.lastIndexOf('\\'));
		String rawMessage = survivedEl.ownText();
		survivedModel.setRawMessage(rawMessage);
		
		for (String mutationOperator : mutationOperators.mutationOperators) {

			if (rawMessage.toLowerCase().contains(mutationOperator.toLowerCase())) {
				
				survivedModel.setMutationOperator(mutationOperator);
				
				break;
			}
		}
		
		String packageName = filePath.substring(tempStr.lastIndexOf('\\') + 1, filePath.lastIndexOf('\\'));
		survivedModel.setPackageName(packageName);
		survivedModel.setClassName(javaFileName);

	}

	/**
	 * Print list of active mutators
	 * 
	 * @param html file of java file report
	 */
	private void showActiveMutatorsfromHtmlFile(File javaHtmlFile) {
		
		Document htmlDoc = null;
		
		try {
			htmlDoc = Jsoup.parse(javaHtmlFile);
		} 
		catch (IOException e) {
			logger.error(e.getMessage());
		}

		if (htmlDoc == null) {
			return;
		}

		Elements elements = htmlDoc.getElementsByAttributeValue("class", "mutator");
		System.out.println("----------------");
		System.out.println("\nActive mutators are\n");
		
		for (Element element : elements) {
			
			System.out.println("- " + element.ownText());
		}
		printActiveMutators = false;
	}
}
