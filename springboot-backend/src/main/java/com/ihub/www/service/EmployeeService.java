package com.ihub.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ihub.www.exception.ResourceNotFoundException;
import com.ihub.www.model.Employee;
import com.ihub.www.repo.EmployeeRepo;

@Service
public class EmployeeService
{
	@Autowired
	EmployeeRepo employeeRepo;
	
	public List<Employee> getAllEmployees()
	{
		 return employeeRepo.findAll();
	}

	public Employee createEmployee( Employee employee)
	{
		return employeeRepo.save(employee);
	}
	
	public Employee getEmployeeById( Long id)
	{
		return employeeRepo.findById(id).orElseThrow(()->new ResourceNotFoundException(" ID Not Found"));
	}
	
	public ResponseEntity<Employee> updateEmployee(Long id, Employee employee)
	{
		Employee emp=employeeRepo.findById(id).orElseThrow(()->new ResourceNotFoundException(" Employee Does Not Exists "));
	        emp.setFirstName(employee.getFirstName());
	        emp.setLastName(employee.getLastName());
	        emp.setEmail(employee.getEmail());
	        Employee updateEmp=employeeRepo.save(emp);
	        return ResponseEntity.ok(updateEmp);
	}
	
	

	public ResponseEntity<HttpStatus> deleteEmployee(long id)
	{
		Employee employee=employeeRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee Does not Exit"));
		employeeRepo.delete(employee);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	
	
}

	
	
	





