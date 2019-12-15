package com.javaspringclub.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaspringclub.entity.TaskDto;
import com.javaspringclub.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	private TaskRepository taskRepository;
	

	
	@Override
	@Transactional
	public List<TaskDto> getTaskDetails() {
		List<TaskDto> list = new ArrayList<TaskDto>();
		taskRepository.findAll().forEach(e -> list.add(e));
		return list;
	}



	@Override
	@Transactional
	public TaskDto getTaskDetailsById(int task_id) {
		TaskDto task = taskRepository.findById(task_id).get();
		return task;
	}



	@Override
	@Transactional
	public void updateTaskDetails(TaskDto taskDto) {
		TaskDto task = taskRepository.findById(taskDto.getTask_id()).get();
		System.out.println("TaskFromDb : "+task.getTask());
		
		if(task != null){
			taskRepository.save(taskDto);
			System.out.println("Task updated successfully : "+task.getTask());
		}
		
	}



	@Override
	@Transactional
	public void deleteTaskDetails(int task_id) {
		taskRepository.deleteById(task_id);
	}



	@Override
	@Transactional
	public void addTaskInfo(TaskDto taskDto) {
		taskRepository.save(taskDto);
		
	}

	

}
