package com.nagendra.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagendra.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
 
}
