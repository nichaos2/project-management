package com.jrp.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jrp.pma.dto.StageCount;
import com.jrp.pma.entities.Project;

public interface ProjectRepository extends CrudRepository<Project, Long> {
	// the override of the method is crucial
	// in the CrudRepository findAll() returns an Iterable
	// whereas we want a List
	@Override
	public List<Project> findAll();

	
	@Query(nativeQuery=true, value = " select stage, count(stage) as stageCnt from project group by stage ;")
	public List<StageCount> stageCount();
}
