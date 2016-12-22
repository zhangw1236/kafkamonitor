package com.roger.monitor.vo;

public class Login implements LoginMBean {
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String status() {
		return "username: " + username + ", password: " + password;
	}
}