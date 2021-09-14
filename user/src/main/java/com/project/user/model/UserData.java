package com.project.user.model;

import java.util.List;

public class UserData {
	
	private List<User> csv;

	public List<User> getCsv() {
		return csv;
	}

	public void setCsv(List<User> csv) {
		this.csv = csv;
	}

	@Override
	public String toString() {
		return "UserData [csv=" + csv + "]";
	}
	
}
