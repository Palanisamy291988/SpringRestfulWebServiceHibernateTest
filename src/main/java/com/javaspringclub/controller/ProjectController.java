package com.javaspringclub.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javaspringclub.constants.RestConstants;
import com.javaspringclub.entity.Project;
import com.javaspringclub.entity.ProjectCompletedTasks;
import com.javaspringclub.entity.ProjectDto;
import com.javaspringclub.entity.ProjectInfoResponseDto;
import com.javaspringclub.entity.ProjectResponseDto;
import com.javaspringclub.entity.ProjectTasks;
import com.javaspringclub.entity.ResponseHeaderDto;
import com.javaspringclub.helper.JsonTransformers;
import com.javaspringclub.repository.ProjectCompletedTasksRepository;
import com.javaspringclub.repository.ProjectInfoRepository;
import com.javaspringclub.repository.ProjectRepository;
import com.javaspringclub.service.ProjectService;

@RestController
public class ProjectController extends RestConstants {

	static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	ProjectCompletedTasksRepository projectCompletedTasksRepository;
	
	@Autowired
	ProjectInfoRepository projectInfoRepository;

	JsonTransformers jsonTransformers = new JsonTransformers();

	public ProjectController() {

	}

	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}

	@RequestMapping(value = "/addProjectInfo/v1", method = RequestMethod.POST)
	public String addProjectInfo(@RequestBody String requestJson) {
		System.out.println("Start addProjectInfo ProjectController");

		String responseJson = null;

		Project projectDto = new Project();
		ResponseHeaderDto responseHeader = new ResponseHeaderDto();
		ProjectResponseDto projectResponseDto = new ProjectResponseDto();
		List<Project> projectList = new ArrayList<Project>();

		try {
			System.out.println("addProjectInfo RequestJson in ProjectController: " + requestJson);
			projectDto = (Project) jsonTransformers.JsonToObject(requestJson, projectDto);
			System.out.println("ProjectName from addProjectInfo: " + projectDto.getProject());

			projectService.addProjectInfo(projectDto);
			System.out.println("ProjectInfo Saved successfully.");

			projectList = projectService.getProjectDetails();

			if (projectList.size() > 0) {
				responseHeader.setResponseMessage(RESPONSE_MESSAGE);
				responseHeader.setStatus(RESPONSE_SUCCESS);
				responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);
				projectResponseDto.setProjectInfo(projectList);
			} else {
				responseHeader.setResponseMessage("Book is empty in request");

			}
			projectResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(projectResponseDto);
			System.out.println("addProjectInfo responseJson in ProjectController: " + responseJson);

			System.out.println("End addProjectInfo ProjectController");
		} catch (Exception e) {

		}

		return responseJson;
	}

	@RequestMapping(value = "/getProjectDetailsById/v1", method = RequestMethod.POST)
	public String getProjectDetailsById(@RequestBody String requestJson) {
		System.out.println("Start getProjectDetailsById ProjectController");
		logger.info("Start getProjectDetailsById ProjectController");
		Project projectDto = new Project();
		String responseJson = null;
		ProjectResponseDto projectResponseDto = new ProjectResponseDto();
		List<Project> projectDtoList = null;

		System.out.println("getProjectDetailsById RequestJson in ProjectController: " + requestJson);
		logger.debug("getProjectDetailsById RequestJson in ProjectController: " + requestJson);
		try {

			JsonTransformers jsonTransformers = new JsonTransformers();
			projectDto = (Project) jsonTransformers.JsonToObject(requestJson, projectDto);
			projectDto = projectService.getProjectDetailsById(projectDto.getProject_id());

			ResponseHeaderDto responseHeader = new ResponseHeaderDto();
			responseHeader.setStatus(RESPONSE_SUCCESS);
			responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);

			if (projectDto != null) {
				projectDtoList = new ArrayList<Project>();
				projectDtoList.add(projectDto);
				responseHeader.setResponseMessage(RESPONSE_MESSAGE);
			} else {
				responseHeader.setResponseMessage("Project does not exist");
			}

			projectResponseDto.setProjectInfo(projectDtoList);
			projectResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(projectResponseDto);
			System.out.println("getProjectDetailsById ResponseJson in ProjectController: " + responseJson);

			System.out.println("End getProjectDetailsById ProjectController");
		} catch (Exception e) {
		}

		return responseJson;
	}

	@RequestMapping(value = "/getProjectDetails/v1", method = RequestMethod.POST)
	public String getProjectDetails() {
		System.out.println("Start getProjectDetails CallUpdate ProjectController");
		String responseJson = null;

		try {

			ResponseHeaderDto responseHeader = new ResponseHeaderDto();
			responseHeader.setStatus(RESPONSE_SUCCESS);
			responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);
			responseHeader.setResponseMessage(RESPONSE_MESSAGE);

			ProjectResponseDto projectResponseDto = new ProjectResponseDto();
			List<Project> projectList = projectService.getProjectDetails();
			System.out.println(
					"ProjectList from getProjectDetails: " + projectList + "projectList size: " + projectList.size());
			projectResponseDto.setProjectInfo(projectList);
			projectResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(projectResponseDto);
			System.out.println("getProjectDetails ResponseJson in ProjectController: " + responseJson);

			System.out.println("End getProjectDetails ProjectController");
		} catch (Exception e) {
		}

		return responseJson;
	}

	@RequestMapping(value = "/updateProjectDetails/v1", method = RequestMethod.POST)
	public String updateProjectDetails(@RequestBody String requestJson) {
		System.out.println("Start updateProjectDetails ProjectController");
		Project projectDto = new Project();
		String responseJson = null;
		ProjectResponseDto projectResponseDto = new ProjectResponseDto();
		List<Project> projectDtoList = new ArrayList<Project>();

		System.out.println("getProjectDetailsById RequestJson in ProjectController: " + requestJson);
		try {

			JsonTransformers jsonTransformers = new JsonTransformers();
			projectDto = (Project) jsonTransformers.JsonToObject(requestJson, projectDto);
			projectService.updateProjectDetails(projectDto);

			projectDtoList = projectService.getProjectDetails();

			ResponseHeaderDto responseHeader = new ResponseHeaderDto();
			responseHeader.setStatus(RESPONSE_SUCCESS);
			responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);

			if (projectDtoList.size() > 0) {
				projectResponseDto.setProjectInfo(projectDtoList);
				responseHeader.setResponseMessage(RESPONSE_MESSAGE);
			} else {
				responseHeader.setResponseMessage("Project does not exist");
			}
			projectResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(projectResponseDto);
			System.out.println("updateProjectDetails ResponseJson in ProjectController: " + responseJson);

			System.out.println("End updateProjectDetails ProjectController");
		} catch (Exception e) {
		}

		return responseJson;
	}

	@RequestMapping(value = "/deleteProjectDetails/v1", method = RequestMethod.POST)
	public String deleteProjectDetails(@RequestBody String requestJson) {
		System.out.println("Start deleteProjectDetails ProjectController");
		Project projectDto = new Project();
		String responseJson = null;
		ProjectResponseDto projectResponseDto = new ProjectResponseDto();

		System.out.println("deleteProjectDetails RequestJson in ProjectController: " + requestJson);
		try {

			JsonTransformers jsonTransformers = new JsonTransformers();
			projectDto = (Project) jsonTransformers.JsonToObject(requestJson, projectDto);
			projectService.deleteProjectDetails(projectDto.getProject_id());

			List<Project> projectDtoList = new ArrayList<Project>();
			projectDtoList = projectService.getProjectDetails();
			System.out.println("ProjectList after delete: " + projectDtoList.size());

			ResponseHeaderDto responseHeader = new ResponseHeaderDto();
			responseHeader.setStatus(RESPONSE_SUCCESS);
			responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);

			if (projectDtoList.size() > 0) {
				responseHeader.setResponseMessage(RESPONSE_MESSAGE);
				projectResponseDto.setProjectInfo(projectDtoList);
			} else {
				responseHeader.setResponseMessage("Project does not exist");
			}

			projectResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(projectResponseDto);
			System.out.println("deleteProjectDetails ResponseJson in ProjectController: " + responseJson);

			System.out.println("End deleteProjectDetails ProjectController");
		} catch (Exception e) {
		}

		return responseJson;
	}
	
	
	@RequestMapping(value = "/getProjectInfo/v1", method = RequestMethod.POST)
	public String getProjectInfo() {
		System.out.println("Start getProjectInfo ProjectController");
		String responseJson = null;
		
		List<ProjectDto> projectDtoList = new ArrayList<ProjectDto>();

		try {

			ResponseHeaderDto responseHeader = new ResponseHeaderDto();
			responseHeader.setStatus(RESPONSE_SUCCESS);
			responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);
			responseHeader.setResponseMessage(RESPONSE_MESSAGE);

			List<Project> projectList = projectService.getProjectDetails();
			System.out.println("ProjectList from getProjectDetails: " + projectList + "projectList size: " + projectList.size());
			
			//Response Processing.
			
			ProjectInfoResponseDto projectInfoResponseDto = new ProjectInfoResponseDto();
			
			for(Project project :projectList){
				
				ProjectDto projectDto = new ProjectDto();
				projectDto.setProject_id(project.getProject_id());
				projectDto.setProject(project.getProject());
				projectDto.setStart_date(project.getStart_date());
				projectDto.setEnd_date(project.getEnd_date());
				projectDto.setPriority(project.getPriority());
				
				System.out.println("ProjectID :"+project.getProject_id());
				ProjectTasks projectTasks = projectInfoRepository.getNoOfTasks(project.getProject_id());
				System.out.println("NoOfTasks: "+projectTasks.getNoOfTasks());
				
				ProjectCompletedTasks  projectCompletedTasks  = projectCompletedTasksRepository.getCompletedTasks(project.getProject_id());
				System.out.println("ProjectCompletedTasks: "+projectCompletedTasks.getCompletedTasks());
				
				projectDto.setNoOfTasks(projectTasks.getNoOfTasks());
				projectDto.setCompletedTasks(projectCompletedTasks.getCompletedTasks());
				
				projectDtoList.add(projectDto);
				
			}
			projectInfoResponseDto.setProjectInfo(projectDtoList);
			projectInfoResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(projectInfoResponseDto);
			System.out.println("getProjectInfo ResponseJson in ProjectController: " + responseJson);

			System.out.println("End getProjectInfo ProjectController");
		} catch (Exception e) {
		}

		return responseJson;
	}
	
	
	
	@RequestMapping(value = "/getSortProjectByStartDate/v1", method = RequestMethod.POST)
	public String getSortProjectByStartDate() {
		System.out.println("Start getSortProjectByStartDate ProjectController");
		String responseJson = null;
		
		List<ProjectDto> projectDtoList = new ArrayList<ProjectDto>();

		try {

			ResponseHeaderDto responseHeader = new ResponseHeaderDto();
			responseHeader.setStatus(RESPONSE_SUCCESS);
			responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);
			responseHeader.setResponseMessage(RESPONSE_MESSAGE);

			List<Project> projectList = projectRepository.getSortProjectByStartDate();
			System.out.println("ProjectList from getProjectDetails: " + projectList + "projectList size: " + projectList.size());
			
			//Response Processing.
			
			ProjectInfoResponseDto projectInfoResponseDto = new ProjectInfoResponseDto();
			
			for(Project project :projectList){
				
				ProjectDto projectDto = new ProjectDto();
				projectDto.setProject_id(project.getProject_id());
				projectDto.setProject(project.getProject());
				projectDto.setStart_date(project.getStart_date());
				projectDto.setEnd_date(project.getEnd_date());
				projectDto.setPriority(project.getPriority());
				
				System.out.println("ProjectID :"+project.getProject_id());
				ProjectTasks projectTasks = projectInfoRepository.getNoOfTasks(project.getProject_id());
				System.out.println("NoOfTasks: "+projectTasks.getNoOfTasks());
				
				ProjectCompletedTasks  projectCompletedTasks  = projectCompletedTasksRepository.getCompletedTasks(project.getProject_id());
				System.out.println("ProjectCompletedTasks: "+projectCompletedTasks.getCompletedTasks());
				
				projectDto.setNoOfTasks(projectTasks.getNoOfTasks());
				projectDto.setCompletedTasks(projectCompletedTasks.getCompletedTasks());
				
				projectDtoList.add(projectDto);
				
			}
			projectInfoResponseDto.setProjectInfo(projectDtoList);
			projectInfoResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(projectInfoResponseDto);
			System.out.println("getSortProjectByStartDate ResponseJson in ProjectController: " + responseJson);

			System.out.println("End getSortProjectByStartDate ProjectController");
		} catch (Exception e) {
		}

		return responseJson;
	}
	
	
	
	@RequestMapping(value = "/getSortProjectByEndDate/v1", method = RequestMethod.POST)
	public String getSortProjectByEndDate() {
		System.out.println("Start getSortProjectByEndDate ProjectController");
		String responseJson = null;
		
		List<ProjectDto> projectDtoList = new ArrayList<ProjectDto>();

		try {

			ResponseHeaderDto responseHeader = new ResponseHeaderDto();
			responseHeader.setStatus(RESPONSE_SUCCESS);
			responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);
			responseHeader.setResponseMessage(RESPONSE_MESSAGE);

			List<Project> projectList = projectRepository.getSortProjectByEndDate();
			System.out.println("ProjectList from getProjectDetails: " + projectList + "projectList size: " + projectList.size());
			
			//Response Processing.
			
			ProjectInfoResponseDto projectInfoResponseDto = new ProjectInfoResponseDto();
			
			for(Project project :projectList){
				
				ProjectDto projectDto = new ProjectDto();
				projectDto.setProject_id(project.getProject_id());
				projectDto.setProject(project.getProject());
				projectDto.setStart_date(project.getStart_date());
				projectDto.setEnd_date(project.getEnd_date());
				projectDto.setPriority(project.getPriority());
				
				System.out.println("ProjectID :"+project.getProject_id());
				ProjectTasks projectTasks = projectInfoRepository.getNoOfTasks(project.getProject_id());
				System.out.println("NoOfTasks: "+projectTasks.getNoOfTasks());
				
				ProjectCompletedTasks  projectCompletedTasks  = projectCompletedTasksRepository.getCompletedTasks(project.getProject_id());
				System.out.println("ProjectCompletedTasks: "+projectCompletedTasks.getCompletedTasks());
				
				projectDto.setNoOfTasks(projectTasks.getNoOfTasks());
				projectDto.setCompletedTasks(projectCompletedTasks.getCompletedTasks());
				
				projectDtoList.add(projectDto);
				
			}
			projectInfoResponseDto.setProjectInfo(projectDtoList);
			projectInfoResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(projectInfoResponseDto);
			System.out.println("getSortProjectByEndDate ResponseJson in ProjectController: " + responseJson);

			System.out.println("End getSortProjectByEndDate ProjectController");
		} catch (Exception e) {
		}

		return responseJson;
	}

	
	
	@RequestMapping(value = "/getSortProjectByPriority/v1", method = RequestMethod.POST)
	public String getSortProjectByPriority() {
		System.out.println("Start getSortProjectByPriority ProjectController");
		String responseJson = null;
		
		List<ProjectDto> projectDtoList = new ArrayList<ProjectDto>();

		try {

			ResponseHeaderDto responseHeader = new ResponseHeaderDto();
			responseHeader.setStatus(RESPONSE_SUCCESS);
			responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);
			responseHeader.setResponseMessage(RESPONSE_MESSAGE);

			List<Project> projectList = projectRepository.getSortProjectByPriority();
			System.out.println("ProjectList from getProjectDetails: " + projectList + "projectList size: " + projectList.size());
			
			//Response Processing.
			
			ProjectInfoResponseDto projectInfoResponseDto = new ProjectInfoResponseDto();
			
			for(Project project :projectList){
				
				ProjectDto projectDto = new ProjectDto();
				projectDto.setProject_id(project.getProject_id());
				projectDto.setProject(project.getProject());
				projectDto.setStart_date(project.getStart_date());
				projectDto.setEnd_date(project.getEnd_date());
				projectDto.setPriority(project.getPriority());
				
				System.out.println("ProjectID :"+project.getProject_id());
				ProjectTasks projectTasks = projectInfoRepository.getNoOfTasks(project.getProject_id());
				System.out.println("NoOfTasks: "+projectTasks.getNoOfTasks());
				
				ProjectCompletedTasks  projectCompletedTasks  = projectCompletedTasksRepository.getCompletedTasks(project.getProject_id());
				System.out.println("ProjectCompletedTasks: "+projectCompletedTasks.getCompletedTasks());
				
				projectDto.setNoOfTasks(projectTasks.getNoOfTasks());
				projectDto.setCompletedTasks(projectCompletedTasks.getCompletedTasks());
				
				projectDtoList.add(projectDto);
				
			}
			projectInfoResponseDto.setProjectInfo(projectDtoList);
			projectInfoResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(projectInfoResponseDto);
			System.out.println("getSortProjectByPriority ResponseJson in ProjectController: " + responseJson);

			System.out.println("End getSortProjectByPriority ProjectController");
		} catch (Exception e) {
		}

		return responseJson;
	}


}
