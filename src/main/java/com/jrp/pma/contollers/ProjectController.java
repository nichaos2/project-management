package com.jrp.pma.contollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;

// we need the controller for the form in the template new-project

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;

	@GetMapping("/new")
	public String displayProjectForm(Model model) { // model binds the object to the template
		Project aProject = new Project();
		model.addAttribute("project", aProject);
		
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("allEmployees", employees);

		return "projects/new-project";
	}

	// handle submission from the form via the action = /project/save
	// this method works for OneToMany Relation where we need all this code to save the PRoject andn the Employee
	// it is not used to the actual project rigth now but kept to remember how ot should be for a OneToMany relation case
	@PostMapping("/save") // PostMapping is another way instead of using the attribute method in the
							// @RequestMapping
	public String createProject(Project project, @RequestParam List<Long> employees, Model model) { // model is send from the template
		// this handles saving the project to the database
		proRepo.save(project);
		
		// this handles the employee property projectId, which is the foreign key 
		Iterable<Employee> chosenEmployees = empRepo.findAllById(employees); // find all the employees from the id list we chose in the form
		for (Employee emp : chosenEmployees) {
			emp.setProject(project); // update the employee object project 
			empRepo.save(emp); // save the new employee
		}
		
		// use redirect to prevent duplicate submissions
		return "redirect:/projects"; // we need the first /
	}
	
	
	// handle submission from the form via the action = /project/save
	@PostMapping("/save2") // PostMapping is another way instead of using the attribute method in the
							// @RequestMapping
	public String createProject2(Project project, Model model) { // model is send from the template
		
		// this handles saving the project to the database; 
		// and thanks to Cascade the Employee object is saved to the database too 
		// this is also thanks to the ManyToMAny relation
		proRepo.save(project);

		
		// use redirect to prevent duplicate submissions
		return "redirect:/projects"; // we need the first /
	}
	
	@GetMapping
	public String displayProjectList(Model model) { // model binds the object to the template
		// query database for Projects
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projects",projects);
		

		return "projects/list-projects";
	}

}
