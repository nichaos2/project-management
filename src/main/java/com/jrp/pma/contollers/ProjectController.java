package com.jrp.pma.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jrp.pma.entities.Project;


// we need the controller for the form in the template new-project

@Controller
@RequestMapping("/projects") 
public class ProjectController {

	@GetMapping("/new")
	public String displayProjectForm(Model model) { // model binds the object to the template
		Project aProject = new Project();
		model.addAttribute("project", aProject);
		
		return "new-project";
	}
	
	// handle submission from the form via the action = /project/save
	@PostMapping("/save") // PostMapping is another way instead of using the attribute method in the @RequestMapping
	public String createProject(Project project, Model model) { // model is send from the template
		//this should handle saving to the database
		
	}
	
	
}
