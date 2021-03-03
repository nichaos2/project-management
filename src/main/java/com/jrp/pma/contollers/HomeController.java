package com.jrp.pma.contollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;

@Controller
public class HomeController {

	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository emplRepo;
	
	@GetMapping("/")
	public String displayHome(Model model) {
		
		// query database for Projects
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projectsList",projects);
		
		//query database for Employees --> EmployeeProject
		List<EmployeeProject> employeeProjectsCnt = emplRepo.employeeProjects();
		model.addAttribute("employeeProjectsCnt", employeeProjectsCnt);
		
		return "main/home";
	}
}
