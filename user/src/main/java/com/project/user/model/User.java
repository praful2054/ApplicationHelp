package com.project.user.model;

public class User {
	
	private String vin;

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	@Override
	public String toString() {
		return "User [vin=" + vin + "]";
	}
	
	

}
