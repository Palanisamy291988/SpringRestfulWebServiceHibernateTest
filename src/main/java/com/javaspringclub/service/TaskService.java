package com.javaspringclub.service;

import java.util.List;

import com.javaspringclub.entity.TaskDto;

public interface TaskService {

	public List<TaskDto> getTaskDetails();

	public TaskDto getTaskDetailsById(int task_id);

	public void updateTaskDetails(TaskDto taskDto);

	public void deleteTaskDetails(int task_id);

	public void addTaskInfo(TaskDto taskDto);

}
