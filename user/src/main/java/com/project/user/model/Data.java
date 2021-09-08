package com.project.user.model;

import java.util.List;

public class Data {
	private List<UserData> csv;

	public List<UserData> getCsv() {
		return csv;
	}

	public void setCsv(List<UserData> csv) {
		this.csv = csv;
	}

	@Override
	public String toString() {
		return "Data [csv=" + csv + "]";
	}

}
