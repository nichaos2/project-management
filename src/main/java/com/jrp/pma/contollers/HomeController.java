package com.jrp.pma.contollers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.dto.StageCount;
import com.jrp.pma.entities.Project;

@Controller
public class HomeController {

	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository emplRepo;
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		// query database for Projects
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projectsList",projects);
		
		List<StageCount> stageCounts= proRepo.stageCount();
		// convert the object StageCount to JSON to be used by javascrip; that's why we need also a map object
		Map<String, Object> map = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(stageCounts); // [ [INPROGRESS, 2 ], [NOTSTARTED,1 ], [COMPLETED,1] ]
		model.addAttribute("stageCnt",jsonString); // this will go to the view
		
		//query database for Employees --> EmployeeProject
		List<EmployeeProject> employeeProjectsCnt = emplRepo.employeeProjects();
		model.addAttribute("employeeProjectsCnt", employeeProjectsCnt);
		
		return "main/home";
	}
}
