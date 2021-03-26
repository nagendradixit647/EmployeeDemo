package com.nagendra.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.nagendra.model.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmployee();

	public void saveEmployee(Employee employee);

	public Employee findEmployeeById(int id);

	public void deleteEmployeeById(int id);
	
	public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
