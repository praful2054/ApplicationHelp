package com.project.user.model;

import java.util.List;

public class Data {
	
	private String user;
	private List<String> Userlist;
	
	
	
	public String getUser() {
		return user;
	}



	public void setUser(String user) {
		this.user = user;
	}



	public List<String> getUserlist() {
		return Userlist;
	}



	public void setUserlist(List<String> userlist) {
		Userlist = userlist;
	}



	@Override
	public String toString() {
		return "Data [user=" + user + ", Userlist=" + Userlist + "]";
	}

	
}
