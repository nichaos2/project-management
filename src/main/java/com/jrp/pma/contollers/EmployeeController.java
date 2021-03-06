package com.jrp.pma.contollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.entities.Employee;


@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeRepository emplRepo;
	
	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {		
		Employee employee = new Employee();
		model.addAttribute("employee", employee);	
		
		return "employees/new-employee";
	}
	
	@PostMapping("/save")
	public String createEmployee(Employee employee, Model model) {
		
		emplRepo.save(employee);
		
		return "redirect:/employees";
	}
	
	@GetMapping
	public String displayEmployeeList(Model model) {		
		
		//query database for Employees
		List<Employee> employees = emplRepo.findAll();
		model.addAttribute("employees", employees);
		
		return "employees/list-employees";
	}
}
