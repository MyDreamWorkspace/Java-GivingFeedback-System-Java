package com.mutation.model;

/**
 * Model class of Survived mutation
 *
 */
public class SurvivedModel {

	private String packageName;
	private String className;
	private String lineNumber;
	private String lineJavaCode;
	private String mutationOperator;
	private String originalExpression;
	private String mutatedExpression;
	private String rawMessage;

	public String getPackageName() {
		
		return packageName;
	}

	public void setPackageName(String packageName) {
		
		this.packageName = packageName;
	}

	public String getClassName() {
		
		return className;
	}

	public void setClassName(String className) {
		
		this.className = className;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		
		this.lineNumber = lineNumber;
	}

	public String getLineJavaCode() {
		
		return lineJavaCode;
	}

	public void setLineJavaCode(String lineJavaCode) {
		
		this.lineJavaCode = lineJavaCode;
	}

	public String getMutationOperator() {
		
		return mutationOperator;
	}

	public void setMutationOperator(String mutationOperator) {
		
		this.mutationOperator = mutationOperator;
	}

	public String getOriginalExpression() {
		
		return originalExpression;
	}

	public void setOriginalExpression(String originalExpression) {
		
		this.originalExpression = originalExpression;
	}

	public String getMutatedExpression() {
		return mutatedExpression;
	}

	public void setMutatedExpression(String mutatedExpression) {
		this.mutatedExpression = mutatedExpression;
	}

	public String getRawMessage() {
		return rawMessage;
	}

	public void setRawMessage(String rawMessage) {
		this.rawMessage = rawMessage;
	}

}
