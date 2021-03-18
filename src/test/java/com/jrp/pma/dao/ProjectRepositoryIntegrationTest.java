package com.jrp.pma.dao;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test; // not the package org.junit.Test
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import com.jrp.pma.entities.Project;

// Because the test is in the package with the naming convention pringboot can load teh context without defining it
@SpringBootTest
// how the test will run? we need a runner
@RunWith(SpringRunner.class)
// plus we do not need to specify teh test type
//@DataJpaTest
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
