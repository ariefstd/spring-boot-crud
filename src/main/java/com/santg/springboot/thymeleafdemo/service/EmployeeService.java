package com.santg.springboot.thymeleafdemo.service;

import java.util.List;

import com.santg.springboot.thymeleafdemo.entity;

public interface EmployeeService {

	public List<Employee> findAll();
	
	public Employee findById(int id);
	
	public void save(Employee Employees);
	
	public void deleteById(int id);
	
}
