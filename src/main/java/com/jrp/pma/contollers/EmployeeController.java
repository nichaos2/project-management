package com.jrp.pma.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/employees")
public class EmployeeController {

	
	public String displayEmployeeForm() {
		
		
		return "new-employee";
	}
	
	
}
