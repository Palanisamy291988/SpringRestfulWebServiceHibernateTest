package com.javaspringclub.service;

import java.util.List;

import com.javaspringclub.entity.ParentDto;

public interface ParentService {

	public List<ParentDto> getParentDetails();

	public ParentDto getParentDetailsById(int task_id);

	public void updateParentDetails(ParentDto parentDto);

	public void deleteParentDetails(int parent_id);

	public void addParentInfo(ParentDto parentDto);

}
