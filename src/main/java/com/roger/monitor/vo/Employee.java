package com.roger.monitor.vo;
import java.util.Date;

public class Employee {
	private int id;
	private int user_id;
	private int age;
	private String first_name;
	private String second_name;
	private Date date;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return user_id;
	}
	public void setUserId(int userId) {
		this.user_id = userId;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getFirstName() {
		return first_name;
	}
	public void setFirstName(String firstName) {
		this.first_name = firstName;
	}
	public String getSecondName() {
		return second_name;
	}
	public void setSecondName(String secondName) {
		this.second_name = secondName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String toString() {
		return user_id + "|" + age + "|" + first_name + "|" + second_name + "|" + date;
	}
}