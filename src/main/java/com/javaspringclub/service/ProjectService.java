package com.javaspringclub.service;

import java.util.List;

import com.javaspringclub.entity.Project;

public interface ProjectService {
	
	public Project getProjectDetailsById(int id);

	public List<Project> getProjectDetails();

	public void addProjectInfo(Project projectDto);

	public void updateProjectDetails(Project projectDto);
	
	public void deleteProjectDetails(int projectId);

}
