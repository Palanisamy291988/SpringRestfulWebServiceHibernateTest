package com.javaspringclub.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javaspringclub.entity.ProjectCompletedTasks;

@Repository("projectCompletedTasksRepository")
public interface ProjectCompletedTasksRepository extends CrudRepository<ProjectCompletedTasks, Integer>{
	
	static final String GET_COMPLETED_TASKS = " SELECT count(T.TASK) AS completedTasks FROM PROJECT P INNER JOIN TASK T "+
			" ON P.PROJECT_ID = T.PROJECT_ID "+
			" AND P.PROJECT_ID=:projectId "+
			" AND T.STATUS = 'Completed' "+
			" GROUP BY PROJECT ";

	@Query(nativeQuery = true, value=GET_COMPLETED_TASKS)
	public ProjectCompletedTasks getCompletedTasks(@Param("projectId") int projectId);
	

}
