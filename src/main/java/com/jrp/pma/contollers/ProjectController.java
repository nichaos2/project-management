package com.jrp.pma.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jrp.pma.entities.Project;


// we need the controller for the form in the template new-project

@Controller
@RequestMapping("/projects") 
public class ProjectController {

	@RequestMapping("/new")
	public String displayProjectForm(Model model) { // model binds the object in the template
		Project aProject = new Project();
		model.addAttribute("project", aProject);
		
		return "new-project";
	}
	
}
