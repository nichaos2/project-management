package com.jrp.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	@Override
	public List<Employee> findAll();

	// query the projects to retrieve how many projects each employee has
	// this way makes it more efficient becuase it is the sql that does all the work
	// instead of the
	// Java ORM which although useful is not the most efficient for lots of entries
	// in the database
	// -- native Query is specific to the database
	// -- doesn't this make it a bit clunky when we change the database?
	@Query(nativeQuery = true, value = "SELECT e.first_name as firstName , e.last_name as lastName , COUNT(pe.employee_id) as projectCount "
			+ "FROM employee e left join project_employee pe ON e.employee_id=pe.employee_id "
			+ "GROUP BY firstName, lastName ORDER BY projectCount  DESC; ")
	public List<EmployeeProject> employeeProjects();

	// we need to create an interface and each entry taht came from the query will
	// be an item of this interface
	// this interface will be in the package dto: data transfer object
	// the spring framework does all the work so we do not need to specialize the
	// implementation in a class
	// in the EmployeeProject interface we will define the methods coming from the
	// way we defined the as attributes in the query

}
