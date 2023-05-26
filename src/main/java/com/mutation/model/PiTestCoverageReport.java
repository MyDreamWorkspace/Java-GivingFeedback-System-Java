package com.mutation.model;

import java.math.BigDecimal;

/**
 * Model class of PI Test Report
 *
 */
public class PiTestCoverageReport {

	private Integer numberOfClasses;
	private BigDecimal lineCoveragePercentage;
	private BigDecimal mutationCoveragePercentage;
	private BigDecimal testStrengthPercentage;

	public PiTestCoverageReport() {

	}

	public PiTestCoverageReport(Integer numberOfClasses, BigDecimal lineCoveragePercentage,
			BigDecimal mutationCoveragePercentage, BigDecimal testStrengthPercentage) {
		
		super();
		
		this.numberOfClasses = numberOfClasses;
		this.lineCoveragePercentage = lineCoveragePercentage;
		this.mutationCoveragePercentage = mutationCoveragePercentage;
		this.testStrengthPercentage = testStrengthPercentage;
	}

	public Integer getNumberOfClasses() {
		
		return numberOfClasses;
	}

	public void setNumberOfClasses(Integer numberOfClasses) {
		
		this.numberOfClasses = numberOfClasses;
	}

	public BigDecimal getLineCoveragePercentage() {
		return lineCoveragePercentage;
	}

	public void setLineCoveragePercentage(BigDecimal lineCoveragePercentage) {
		
		this.lineCoveragePercentage = lineCoveragePercentage;
	}

	public BigDecimal getMutationCoveragePercentage() {
		
		return mutationCoveragePercentage;
	}

	public void setMutationCoveragePercentage(BigDecimal mutationCoveragePercentage) {
		
		this.mutationCoveragePercentage = mutationCoveragePercentage;
	}

	public BigDecimal getTestStrengthPercentage() {
		
		return testStrengthPercentage;
	}

	public void setTestStrengthPercentage(BigDecimal testStrengthPercentage) {
		
		this.testStrengthPercentage = testStrengthPercentage;
	}

	@Override
	public String toString() {
		
		return "PiTestCoverageReport [numberOfClasses=" + numberOfClasses + ", lineCoveragePercentage="
				+ lineCoveragePercentage + ", mutationCoveragePercentage=" + mutationCoveragePercentage
				+ ", testStrengthPercentage=" + testStrengthPercentage + "]";
	}

}
