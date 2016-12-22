package com.roger.monitor.vo;

public interface LoginMBean {
	public String getUsername();
	public void setUsername(String username);
	public String getPassword();
	public void setPassword(String password);
	
	public String status();
}
