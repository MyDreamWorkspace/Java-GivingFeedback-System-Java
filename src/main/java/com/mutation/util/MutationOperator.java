package com.mutation.util;

import java.util.Map;
import java.util.TreeMap;

/**
 * Model class of mutation operator
 *
 */
public class MutationOperator {

	private String name;
	
	private Map<String, String> conditions = new TreeMap<>();

	public MutationOperator(String name, Map<String, String> conditions) {
		
		super();
		this.name = name;
		this.conditions = conditions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getConditions() {
		return conditions;
	}

	public void setConditions(Map<String, String> conditions) {
		this.conditions = conditions;
	}

}
