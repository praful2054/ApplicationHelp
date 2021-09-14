package com.project.user.model;

import java.util.Date;

public class UserDataResponse {

	private String id;
	private String user;
	private String name;
	private long count;
	private String test;
	private String status;
	private Date Created;
	private Date Updated;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreated() {
		return Created;
	}

	public void setCreated(Date created) {
		Created = created;
	}

	public Date getUpdated() {
		return Updated;
	}

	public void setUpdated(Date updated) {
		Updated = updated;
	}

}
