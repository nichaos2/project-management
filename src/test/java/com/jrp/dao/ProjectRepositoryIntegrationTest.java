package com.jrp.dao;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test; // not the package org.junit.Test
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import com.jrp.pma.ProjectManagementApplication;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Project;

// The folowing annotation are necessary because the file is not in the package pma as in the original project
// so in this context it does not know how to proceed with the test
// usually we use teh spring boot test functionallity


// this annotation points to the class where the project starts
// this brings in all the Beans and the injection we have in our project
// so it loads the application context 
@ContextConfiguration(classes = ProjectManagementApplication.class)
// how the test will run? we need a runner
@RunWith(SpringRunner.class)
// this is useful to create tests specific for database functions
@DataJpaTest
// we need to run some things before or after the test starts; for example to populate the tables of the database or delete the table at the end
@SqlGroup({
		@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = { "classpath:schema.sql",
				"classpath:data.sql" }),
		@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = { "classpath:drop.sql" }) })
public class ProjectRepositoryIntegrationTest {

	@Autowired
	ProjectRepository proRepo;

	@Test
	public void ifNewProjectSaved_thenSuccess() {
		Project newProject = new Project("New Test Project", "COMPLETE", "Test Description");
		proRepo.save(newProject);
		// after the insertion of the new project we are going to have 5 projects in total (4 from the sql population file)
		System.out.println("TEST PHASE");
		System.out.println(proRepo.findAll().size());
		assertEquals(5, proRepo.findAll().size());
	}
}
