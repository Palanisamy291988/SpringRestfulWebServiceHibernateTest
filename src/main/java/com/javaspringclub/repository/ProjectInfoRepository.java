package com.javaspringclub.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javaspringclub.entity.ProjectTasks;

@Repository("projectInfoRepository")
public interface ProjectInfoRepository extends CrudRepository<ProjectTasks, Integer>{
	
	static final String GET_NO_OF_TASKS = " SELECT count(T.TASK) AS noOfTasks FROM PROJECT P INNER JOIN TASK T "+
			" ON P.PROJECT_ID = T.PROJECT_ID "+
			" AND P.PROJECT_ID=:projectId "+
			" GROUP BY PROJECT ";

	@Query(nativeQuery = true, value=GET_NO_OF_TASKS)
	public ProjectTasks getNoOfTasks(@Param("projectId") int projectId);
	

}
