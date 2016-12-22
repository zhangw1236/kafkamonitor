package com.roger.monitor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roger.monitor.dao.UserMapperDao;
import com.roger.monitor.service.UserMapperService;
import com.roger.monitor.vo.Employee;

@Service("userMapperService")
public class UserMapperServiceImpl implements UserMapperService {
	@Autowired
	private UserMapperDao userMapperDao;

	public void setUserMapperDao(UserMapperDao userMapperDao) {
		this.userMapperDao = userMapperDao;
	}

	@Override
	public Employee findById(int id) {
		return userMapperDao.findById(id);
	}

	@Override
	public Employee findByFirstName(String name) {
		return userMapperDao.findByFirstName(name);
	}
}
