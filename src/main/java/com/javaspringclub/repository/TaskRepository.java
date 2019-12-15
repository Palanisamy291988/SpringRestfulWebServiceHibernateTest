package com.javaspringclub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javaspringclub.entity.TaskDto;

@Repository("taskRepository")
public interface TaskRepository extends CrudRepository<TaskDto, Integer>{
	
/*	final static String GET_SUBJECT = "SELECT S.SUBJECT_ID,S.SUBJECT_TITLE,S.DURATION_INHOURS,S.BOOK_ID,B.BOOK_TITLE,B.PRICE,B.VOLUME,B.PUBLISH_DATE FROM SUBJECT S "+
            " INNER JOIN BOOK B "+
            " ON S.BOOK_ID = B.BOOK_ID "+
			   " AND B.PUBLISH_DATE BETWEEN :start_date AND :end_date ";

@Query(nativeQuery = true, value=GET_SUBJECT)
List<Subject> getSubjectByDuration(@Param("start_date") String start_date,@Param("end_date") String end_date);*/
	
	static final String GET_SORT_TASK_BY_START_DATE = "SELECT * FROM TASK ORDER BY START_DATE DESC";
	static final String GET_SORT_TASK_BY_END_DATE = "SELECT * FROM TASK ORDER BY END_DATE DESC";
	static final String GET_SORT_TASK_BY_PRIORITY = "SELECT * FROM TASK ORDER BY PRIORITY DESC";
	static final String GET_SORT_TASK_BY_COMPLETED_STATUS = "SELECT * FROM TASK WHERE LOWER(STATUS) = 'COMPLETED' ";
	
	@Query(nativeQuery = true, value=GET_SORT_TASK_BY_START_DATE)
	List<TaskDto> getSortTaskByStartDate();

	@Query(nativeQuery = true, value=GET_SORT_TASK_BY_END_DATE)
	List<TaskDto> getSortTaskByEndDate();

	@Query(nativeQuery = true, value=GET_SORT_TASK_BY_PRIORITY)
	List<TaskDto> getSortTaskByPriority();
	
	
	@Query(nativeQuery = true, value=GET_SORT_TASK_BY_COMPLETED_STATUS)
	List<TaskDto> getSortTaskByCompletedStatus();
}
