package com.nagendra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nagendra.model.Employee;
import com.nagendra.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "firstName", "asc", model);
	}

	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeFrom(Model model) {
		model.addAttribute("employee", new Employee());
		return "new_employee";

	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		employeeService.saveEmployee(employee);
		return "redirect:/";
	}

	@GetMapping("showUpdateEmployeeForm/{id}")
	public String updateEmployee(@PathVariable("id") int employeeId, Model model) {
		model.addAttribute("employee", employeeService.findEmployeeById(employeeId));
		return "update_employee";
	}

	@GetMapping("deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable("id") int employeeId) {
		employeeService.deleteEmployeeById(employeeId);
		return "redirect:/";
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable("pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 10;
		Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
		model.addAttribute("listEmployee", page.getContent());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equalsIgnoreCase("asc") ? "desc" :"asc");
		
		return "index";
	}

}
