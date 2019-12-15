package com.javaspringclub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javaspringclub.entity.UserDto;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<UserDto, Integer>{
	
	static final String GET_SORT_TASK_BY_FIRST_NAME = "SELECT * FROM USER ORDER BY FIRST_NAME ASC";
	static final String GET_SORT_TASK_BY_LAST_NAME = "SELECT * FROM USER ORDER BY LAST_NAME ASC";
	static final String GET_SORT_TASK_BY_EMPLOYEE_ID = "SELECT * FROM USER ORDER BY EMPLOYEE_ID ASC";
	
	@Query(nativeQuery = true, value=GET_SORT_TASK_BY_FIRST_NAME)
	List<UserDto> getSortUserByFirstName();

	@Query(nativeQuery = true, value=GET_SORT_TASK_BY_LAST_NAME)
	List<UserDto> getSortUserByLastName();

	@Query(nativeQuery = true, value=GET_SORT_TASK_BY_EMPLOYEE_ID)
	List<UserDto> getSortUserByEmployeeId();
	
	
}
