package com.lawrence;

public class WrestleEntity {

	private String name;
	private Integer weight;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public WrestleEntity(String name, Integer weight) {
		super();
		this.name = name;
		this.weight = weight;
	}

}
