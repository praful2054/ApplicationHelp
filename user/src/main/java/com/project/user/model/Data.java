package com.project.user.model;

import java.util.List;

public class Data {
	
	private String subscriberId;
	private List<String> vinList;
	
	
	public String getSubscriberId() {
		return subscriberId;
	}
	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}
	public List<String> getVinList() {
		return vinList;
	}
	public void setVinList(List<String> vinList) {
		this.vinList = vinList;
	}
	
	
	public String toString() {
		return "Data [subscriberId=" + subscriberId + ", vinList=" + vinList + "]";
	}
}
