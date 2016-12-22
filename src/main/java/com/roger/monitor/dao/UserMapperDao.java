package com.roger.monitor.dao;

import org.apache.ibatis.annotations.Select;

import com.roger.monitor.vo.Employee;

public interface UserMapperDao {
	@Select("select * from employees where user_id=#{id}")
	public Employee findById(int id);
	
	@Select("select * from employees where first_name=#{findByFirstName}")
	public Employee findByFirstName(String findByFirstName);
}