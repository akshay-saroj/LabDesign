package com.labdesign.enumeration;

public enum ItemMaterial {

	GW("Glass"), C("Chemical"), A("Analytical"), E("Electronic"), D("Paper"), G("Gaseous"), O("Others");
	
	private final String description;
	private ItemMaterial(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
}
