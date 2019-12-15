package com.javaspringclub.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javaspringclub.entity.ParentDto;

@Repository("parentRepository")
public interface ParentRepository extends CrudRepository<ParentDto, Integer>{
	

}
