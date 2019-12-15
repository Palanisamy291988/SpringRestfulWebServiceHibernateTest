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
import com.javaspringclub.entity.ResponseHeaderDto;
import com.javaspringclub.entity.TaskDto;
import com.javaspringclub.entity.TaskResponseDto;
import com.javaspringclub.helper.JsonTransformers;
import com.javaspringclub.repository.TaskRepository;
import com.javaspringclub.service.TaskService;

@RestController
public class TaskController extends RestConstants {

	static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

	@Autowired
	private TaskService taskService;
	
	@Autowired
	TaskRepository taskRepository;

	JsonTransformers jsonTransformers = new JsonTransformers();

	public TaskController() {

	}

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	

	@RequestMapping(value = "/getTaskDetails/v1", method = RequestMethod.POST)
	public String getTaskDetails() {
		System.out.println("Start getTaskDetails TaskController");
		String responseJson = null;

		try {

			ResponseHeaderDto responseHeader = new ResponseHeaderDto();
			responseHeader.setStatus(RESPONSE_SUCCESS);
			responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);
			responseHeader.setResponseMessage(RESPONSE_MESSAGE);

			TaskResponseDto taskResponseDto = new TaskResponseDto();
			List<TaskDto> taskList = taskService.getTaskDetails();
			System.out.println("TaskList from getTaskDetails: " + taskList + "taskList size: " + taskList.size());
			taskResponseDto.setTaskInfo(taskList);
			taskResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(taskResponseDto);
			System.out.println("getTaskDetails ResponseJson in TaskController: " + responseJson);

			System.out.println("End getTaskDetails TaskController");
		} catch (Exception e) {
		}

		return responseJson;
	}
	
	
	
	
	@RequestMapping(value = "/getTaskDetailsById/v1", method = RequestMethod.POST)
	public String getTaskDetailsById(@RequestBody String requestJson) {
		System.out.println("Start getTaskDetailsById TaskController");
		logger.info("Start getTaskDetailsById TaskController");
		TaskDto taskDto = new TaskDto();
		String responseJson = null;
		TaskResponseDto taskResponseDto = new TaskResponseDto();
		List<TaskDto> taskDtoList = null;

		System.out.println("getTaskDetailsById RequestJson in TaskController: " + requestJson);
		logger.debug("getProjectDetailsById RequestJson in TaskController: " + requestJson);
		try {

			JsonTransformers jsonTransformers = new JsonTransformers();
			taskDto = (TaskDto) jsonTransformers.JsonToObject(requestJson, taskDto);
			taskDto = taskService.getTaskDetailsById(taskDto.getTask_id());

			ResponseHeaderDto responseHeader = new ResponseHeaderDto();
			responseHeader.setStatus(RESPONSE_SUCCESS);
			responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);

			if (taskDto != null) {
				taskDtoList = new ArrayList<TaskDto>();
				taskDtoList.add(taskDto);
				responseHeader.setResponseMessage(RESPONSE_MESSAGE);
			} else {
				responseHeader.setResponseMessage("Project does not exist");
			}

			taskResponseDto.setTaskInfo(taskDtoList);
			taskResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(taskResponseDto);
			System.out.println("getTaskDetailsById ResponseJson in TaskController: " + responseJson);

			System.out.println("End getTaskDetailsById TaskController");
		} catch (Exception e) {
		}

		return responseJson;
	}
	
	
	@RequestMapping(value = "/updateTaskDetails/v1", method = RequestMethod.POST)
	public String updateTaskDetails(@RequestBody String requestJson) {
		System.out.println("Start updateTaskDetails ProjectController");
		TaskDto taskDto = new TaskDto();
		String responseJson = null;
		TaskResponseDto taskResponseDto = new TaskResponseDto();
		List<TaskDto> taskDtoList = new ArrayList<TaskDto>();

		System.out.println("updateTaskDetails RequestJson in TaskController: " + requestJson);
		try {

			taskDto = (TaskDto) jsonTransformers.JsonToObject(requestJson, taskDto);
			taskService.updateTaskDetails(taskDto);

			taskDtoList = taskService.getTaskDetails();
			System.out.println("TaskList in TaskController :"+taskDtoList.size());
			
			if(taskDtoList != null){
				for(TaskDto task : taskDtoList){
					String taskName = task.getTask();
					System.out.println("TaskName: "+taskName);
				}
			}

			ResponseHeaderDto responseHeader = new ResponseHeaderDto();
			responseHeader.setStatus(RESPONSE_SUCCESS);
			responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);

			if (taskDtoList.size() > 0) {
				taskResponseDto.setTaskInfo(taskDtoList);
				responseHeader.setResponseMessage(RESPONSE_MESSAGE);
			} else {
				responseHeader.setResponseMessage("Task does not exist");
			}
			taskResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(taskResponseDto);
			System.out.println("updateTaskDetails ResponseJson in TaskController: " + responseJson);

			System.out.println("End updateTaskDetails TaskController");
		} catch (Exception e) {
		}

		return responseJson;
	}
	
	
	
	@RequestMapping(value = "/deleteTaskDetails/v1", method = RequestMethod.POST)
	public String deleteTaskDetails(@RequestBody String requestJson) {
		System.out.println("Start deleteTaskDetails TaskController");
		TaskDto taskDto = new TaskDto();
		String responseJson = null;
		TaskResponseDto taskResponseDto = new TaskResponseDto();
		List<TaskDto> taskDtoList = new ArrayList<TaskDto>();

		System.out.println("deleteTaskDetails RequestJson in TaskController: " + requestJson);
		try {

			taskDto = (TaskDto) jsonTransformers.JsonToObject(requestJson, taskDto);
			taskService.deleteTaskDetails(taskDto.getTask_id());

			taskDtoList = taskService.getTaskDetails();
			System.out.println("TaskList after delete: " + taskDtoList.size());

			ResponseHeaderDto responseHeader = new ResponseHeaderDto();
			responseHeader.setStatus(RESPONSE_SUCCESS);
			responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);

			if (taskDtoList.size() > 0) {
				responseHeader.setResponseMessage(RESPONSE_MESSAGE);
				taskResponseDto.setTaskInfo(taskDtoList);
			} else {
				responseHeader.setResponseMessage("Task does not exist");
			}

			taskResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(taskResponseDto);
			System.out.println("deleteTaskDetails ResponseJson in TaskController: " + responseJson);

			System.out.println("End deleteTaskDetails TaskController");
		} catch (Exception e) {
		}

		return responseJson;
	}
	
	// Sort Task By Start Date
	
	@RequestMapping(value = "/getSortTaskByStartDate/v1", method = RequestMethod.POST)
	public String getSortTaskByStartDate() {
		System.out.println("Start getSortTaskByStartDate TaskController");
		String responseJson = null;

		try {

			ResponseHeaderDto responseHeader = new ResponseHeaderDto();
			responseHeader.setStatus(RESPONSE_SUCCESS);
			responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);
			responseHeader.setResponseMessage(RESPONSE_MESSAGE);

			TaskResponseDto taskResponseDto = new TaskResponseDto();
			List<TaskDto> taskList = taskRepository.getSortTaskByStartDate();
			System.out.println("TaskList from getSortTaskByStartDate: " + taskList + "taskList size: " + taskList.size());
			taskResponseDto.setTaskInfo(taskList);
			taskResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(taskResponseDto);
			System.out.println("getSortTaskByStartDate ResponseJson in TaskController: " + responseJson);

			System.out.println("End getSortTaskByStartDate TaskController");
		} catch (Exception e) {
		}

		return responseJson;
	}
	
	// Sort Task By End Date
	
	@RequestMapping(value = "/getSortTaskByEndDate/v1", method = RequestMethod.POST)
	public String getSortTaskByEndDate() {
		System.out.println("Start getSortTaskByEndDate TaskController");
		String responseJson = null;

		try {

			ResponseHeaderDto responseHeader = new ResponseHeaderDto();
			responseHeader.setStatus(RESPONSE_SUCCESS);
			responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);
			responseHeader.setResponseMessage(RESPONSE_MESSAGE);

			TaskResponseDto taskResponseDto = new TaskResponseDto();
			List<TaskDto> taskList = taskRepository.getSortTaskByEndDate();
			System.out.println("TaskList from getSortTaskByEndDate: " + taskList + "taskList size: " + taskList.size());
			taskResponseDto.setTaskInfo(taskList);
			taskResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(taskResponseDto);
			System.out.println("getSortTaskByEndDate ResponseJson in TaskController: " + responseJson);

			System.out.println("End getSortTaskByEndDate TaskController");
		} catch (Exception e) {
		}

		return responseJson;
	}
	
	
	// Sort Task By Priority
	
	@RequestMapping(value = "/getSortTaskByPriority/v1", method = RequestMethod.POST)
	public String getSortTaskByPriority() {
		System.out.println("Start getSortTaskByPriority TaskController");
		String responseJson = null;

		try {

			ResponseHeaderDto responseHeader = new ResponseHeaderDto();
			responseHeader.setStatus(RESPONSE_SUCCESS);
			responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);
			responseHeader.setResponseMessage(RESPONSE_MESSAGE);

			TaskResponseDto taskResponseDto = new TaskResponseDto();
			List<TaskDto> taskList = taskRepository.getSortTaskByPriority();
			System.out.println("TaskList from getSortTaskByPriority: " + taskList + "taskList size: " + taskList.size());
			taskResponseDto.setTaskInfo(taskList);
			taskResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(taskResponseDto);
			System.out.println("getSortTaskByPriority ResponseJson in TaskController: " + responseJson);

			System.out.println("End getSortTaskByPriority TaskController");
		} catch (Exception e) {
		}

		return responseJson;
	}
	
	// Sort Task By Completed Status
	
	@RequestMapping(value = "/getSortTaskByCompletedStatus/v1", method = RequestMethod.POST)
	public String getSortTaskByCompletedStatus() {
		System.out.println("Start getSortTaskByCompletedStatus TaskController");
		String responseJson = null;

		try {

			ResponseHeaderDto responseHeader = new ResponseHeaderDto();
			responseHeader.setStatus(RESPONSE_SUCCESS);
			responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);
			responseHeader.setResponseMessage(RESPONSE_MESSAGE);

			TaskResponseDto taskResponseDto = new TaskResponseDto();
			List<TaskDto> taskList = taskRepository.getSortTaskByCompletedStatus();
			System.out.println("TaskList from getSortTaskByCompletedStatus: " + taskList + "taskList size: " + taskList.size());
			taskResponseDto.setTaskInfo(taskList);
			taskResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(taskResponseDto);
			System.out.println("getSortTaskByCompletedStatus ResponseJson in TaskController: " + responseJson);

			System.out.println("End getSortTaskByCompletedStatus TaskController");
		} catch (Exception e) {
		}

		return responseJson;
	}
	
	
	
	@RequestMapping(value = "/addTaskInfo/v1", method = RequestMethod.POST)
	public String addTaskInfo(@RequestBody String requestJson) {
		System.out.println("Start addTaskInfo TaskController");

		TaskDto taskDto = new TaskDto();
		String responseJson = null;
		TaskResponseDto taskResponseDto = new TaskResponseDto();
		List<TaskDto> taskDtoList = new ArrayList<TaskDto>();
		ResponseHeaderDto responseHeader = new ResponseHeaderDto();


		try {
			System.out.println("addTaskInfo RequestJson in TaskController: " + requestJson);
			taskDto = (TaskDto) jsonTransformers.JsonToObject(requestJson, taskDto);
			System.out.println("TaskName from addTaskInfo: " + taskDto.getTask());

			taskService.addTaskInfo(taskDto);
			System.out.println("TaskInfo Saved successfully.");

			taskDtoList = taskService.getTaskDetails();

			if (taskDtoList.size() > 0) {
				responseHeader.setResponseMessage(RESPONSE_MESSAGE);
				responseHeader.setStatus(RESPONSE_SUCCESS);
				responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);
				taskResponseDto.setTaskInfo(taskDtoList);
			} else {
				responseHeader.setResponseMessage("Task is empty in response");

			}
			taskResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(taskResponseDto);
			System.out.println("addTaskInfo responseJson in TaskController: " + responseJson);

			System.out.println("End addTaskInfo TaskController");
		} catch (Exception e) {

		}

		return responseJson;
	}

	
}
