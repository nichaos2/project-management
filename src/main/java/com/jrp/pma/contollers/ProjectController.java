package com.jrp.pma.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


// we need the controller for the form in the template new-project

@Controller
@RequestMapping("/projects") 
public class ProjectController {

	@RequestMapping("/new")
	public String displayProjectForm() {
		return "new-project";
	}
	
}
