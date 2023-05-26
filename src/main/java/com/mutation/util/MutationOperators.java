package com.mutation.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Utility class for mutation operators
 *
 */

public class MutationOperators {

	public MutationOperators() {
		
		mutationOperators.add("Conditional Boundary");
		mutationOperators.add("Increment");
		mutationOperators.add("Invert Negative");
		mutationOperators.add("Math");
		mutationOperators.add("Negated Conditional");
		mutationOperators.add("Return Values");
		mutationOperators.add("Void Method Call");
		mutationOperators.add("Empty return");
		mutationOperators.add("False Return");
		mutationOperators.add("True return");
		mutationOperators.add("Null return");
		mutationOperators.add("Primitive return");

		// Conditionals Boundary

		Map<String, String> conditionalsBoundaryMap = new TreeMap<>();
		
		conditionalsBoundaryMap.put("<", "<=");
		conditionalsBoundaryMap.put("<=", "<");
		conditionalsBoundaryMap.put(">", ">=");
		conditionalsBoundaryMap.put(">=", ">");

		mutationOperatorMap.put("Conditional Boundary",
				new MutationOperator("Conditional Boundary", conditionalsBoundaryMap));

		// Increments

		Map<String, String> incrementsMap = new TreeMap<>();

		incrementsMap.put("--", "++");
		incrementsMap.put("++", "--");
		incrementsMap.put("-", "+");
		incrementsMap.put("+", "-");

		mutationOperatorMap.put("Increment", new MutationOperator("Increment", incrementsMap));

		// Invert Negatives

		Map<String, String> invertNegativesMap = new TreeMap<>();

		invertNegativesMap.put("-", "");

		mutationOperatorMap.put("Invert Negative", new MutationOperator("Invert Negative", invertNegativesMap));

		// Math

		Map<String, String> mathMap = new TreeMap<>();

		mathMap.put("+", "-");
		mathMap.put("-", "+");
		mathMap.put("*", "/");
		mathMap.put("/", "*");
		mathMap.put("%", "*");
		mathMap.put("&", "|");
		mathMap.put("|", "&");
		mathMap.put("^", "&");
		mathMap.put("<<", ">>");
		mathMap.put(">>", "<<");
		mathMap.put(">>>", "<<");

		mutationOperatorMap.put("Math", new MutationOperator("Math", mathMap));

		// Negate Conditionals

		Map<String, String> negateConditionalsMap = new TreeMap<>();

		negateConditionalsMap.put("==", "!=");
		negateConditionalsMap.put("!=", "==");
		negateConditionalsMap.put("<=", ">");
		negateConditionalsMap.put(">=", "<");
		negateConditionalsMap.put("<", ">=");
		negateConditionalsMap.put(">", "<=");

		mutationOperatorMap.put("Negated Conditional",
				new MutationOperator("Negated Conditional", negateConditionalsMap));

		// Return Values

		Map<String, String> returnValuesMap = new TreeMap<>();

		returnValuesMap.put("==", "!=");
		returnValuesMap.put("!=", "==");
		returnValuesMap.put("<=", ">");
		returnValuesMap.put(">=", "<");
		returnValuesMap.put("<", ">=");
		returnValuesMap.put(">", "<=");

		mutationOperatorMap.put("Return Value", new MutationOperator("Return Value", returnValuesMap));

		// Void Method

		Map<String, String> voidMethodMap = new TreeMap<>();
	
		mutationOperatorMap.put("Void Method", new MutationOperator("Void Method", voidMethodMap));

		// Empty returns

		Map<String, String> emptyReturnsMap = new TreeMap<>();

	

		mutationOperatorMap.put("Empty return", new MutationOperator("Empty return", emptyReturnsMap));

		// False returns

		Map<String, String> falseReturnsMap = new TreeMap<>();

	

		mutationOperatorMap.put("False return", new MutationOperator("False return", falseReturnsMap));

		// True returns

		Map<String, String> trueReturnsMap = new TreeMap<>();

		

		mutationOperatorMap.put("True return", new MutationOperator("True return", trueReturnsMap));

		// Null returns

		Map<String, String> nullReturnsMap = new TreeMap<>();

	

		mutationOperatorMap.put("Null returns", new MutationOperator("Null returns", nullReturnsMap));

		// Primitive returns

		Map<String, String> primitiveReturnsMap = new TreeMap<>();

	

		mutationOperatorMap.put("Primitive return", new MutationOperator("Primitive return", primitiveReturnsMap));
	}

	public List<String> mutationOperators = new LinkedList<>();

	public Map<String, MutationOperator> mutationOperatorMap = new TreeMap<>();

}
