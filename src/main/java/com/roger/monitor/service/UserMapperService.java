package com.roger.monitor.service;

import com.roger.monitor.vo.Employee;

public interface UserMapperService {
	public Employee findById(int id);
	public Employee findByFirstName(String name);
}
