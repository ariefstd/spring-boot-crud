package com.santg.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.santg.springboot.thymeleafdemo.entity;
import com.santg.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	// add mapping for "/list"
	@GetMapping("/list")
	public String employeeList(Model model) {
	
		// get employees from data base
		List<Employee> employees = employeeService.findAll();
		
		// add to the spring model
		model.addAttribute("employees", employees);
	 
		return "employees";
	}
	
	// add mapping for "/add" to add new employees
	@GetMapping("/add")
	public String addEmployee(Model model) {
		
		// create model attribute to bind form data
		Employee employee = new Employee();
		
		model.addAttribute("employee", employee);
		
		return "employees";
	}
	
	@GetMapping("/update")
	public String updateEmployee(@RequestParam("employeeId") int id, Model model) {
		
		// get the employee from the service
		Employee employee = employeeService.findById(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("employee", employee);
		
		// send over to our form
		return "employees";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int id) {
		
		// delete employee
		employeeService.deleteById(id);
		
		// return to list
		return "employees";
	}
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		
		// save the employee
		employeeService.save(employee);
		
		// use a redirect to prevent duplicated submissions
		return "employees";
	}
}
