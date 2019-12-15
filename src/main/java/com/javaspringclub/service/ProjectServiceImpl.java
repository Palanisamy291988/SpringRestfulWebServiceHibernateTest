package com.javaspringclub.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaspringclub.entity.Project;
import com.javaspringclub.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService{
	
	@Autowired
	private ProjectRepository projectRepository;
	
	
	@Override
	@Transactional
	public Project getProjectDetailsById(int id) {
		Project project = projectRepository.findById(id).get();
		return project;
	}

	
	@Override
	@Transactional
	public List<Project> getProjectDetails() {
		List<Project> list = new ArrayList<Project>();
		projectRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	@Transactional
	public void addProjectInfo(Project projectDto) {
		System.out.println("ProjectName from addProjectInfo in ProjectServiceImpl: " + projectDto.getProject());
		projectRepository.save(projectDto);
		System.out.println("End addProjectInfo in ProjectServiceImpl");

	}


	@Override
	@Transactional
	public void updateProjectDetails(Project projectDto) {
		// TODO Auto-generated method stub
		Project project = projectRepository.findById(projectDto.getProject_id()).get();
		System.out.println("ProjectFromDb : "+project.getProject());
		
		if(project != null){
			projectRepository.save(projectDto);
			System.out.println("Project updated successfully : "+project.getProject());
		}
		
	}
	
	
	@Override
	@Transactional
	public void deleteProjectDetails(int projectId) {
		// TODO Auto-generated method stub
		projectRepository.deleteById(projectId);
		
	}

}
