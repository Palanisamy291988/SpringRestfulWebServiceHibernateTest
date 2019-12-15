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
import com.javaspringclub.entity.ParentDto;
import com.javaspringclub.entity.ParentResponseDto;
import com.javaspringclub.entity.ResponseHeaderDto;
import com.javaspringclub.helper.JsonTransformers;
import com.javaspringclub.service.ParentService;

@RestController
public class ParentController extends RestConstants {

	static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

	@Autowired
	private ParentService parentService;

	JsonTransformers jsonTransformers = new JsonTransformers();

	public ParentController() {

	}

	public ParentController(ParentService parentService) {
		this.parentService = parentService;
	}

	

	@RequestMapping(value = "/getParentDetails/v1", method = RequestMethod.POST)
	public String getParentDetails() {
		System.out.println("Start getTaskDetails ParentController");
		String responseJson = null;

		try {

			ResponseHeaderDto responseHeader = new ResponseHeaderDto();
			responseHeader.setStatus(RESPONSE_SUCCESS);
			responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);
			responseHeader.setResponseMessage(RESPONSE_MESSAGE);

			ParentResponseDto parentResponseDto = new ParentResponseDto();
			List<ParentDto> parentInfo = parentService.getParentDetails();
			System.out.println("ParentList from getParentDetails: " + parentInfo.size());
			parentResponseDto.setParentInfo(parentInfo);
			parentResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(parentResponseDto);
			System.out.println("getParentDetails ResponseJson in ParentController: " + responseJson);

			System.out.println("End getParentDetails ParentController");
		} catch (Exception e) {
		}

		return responseJson;
	}
	
	
	
	
	@RequestMapping(value = "/getParentDetailsById/v1", method = RequestMethod.POST)
	public String getParentDetailsById(@RequestBody String requestJson) {
		System.out.println("Start getParentDetailsById ParentController");
		ParentDto parentDto = new ParentDto();
		String responseJson = null;
		ParentResponseDto parentResponseDto = new ParentResponseDto();
		List<ParentDto> parentDtoList = null;

		System.out.println("getParentDetailsById RequestJson in ParentController: " + requestJson);
		try {
			parentDto = (ParentDto) jsonTransformers.JsonToObject(requestJson, parentDto);
			parentDto = parentService.getParentDetailsById(parentDto.getParent_id());

			ResponseHeaderDto responseHeader = new ResponseHeaderDto();
			responseHeader.setStatus(RESPONSE_SUCCESS);
			responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);

			if (parentDto != null) {
				parentDtoList = new ArrayList<ParentDto>();
				parentDtoList.add(parentDto);
				responseHeader.setResponseMessage(RESPONSE_MESSAGE);
			} else {
				responseHeader.setResponseMessage("Parent does not exist");
			}

			parentResponseDto.setParentInfo(parentDtoList);
			parentResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(parentResponseDto);
			System.out.println("getParentDetailsById ResponseJson in ParentController: " + responseJson);

			System.out.println("End getParentDetailsById ParentController");
		} catch (Exception e) {
		}

		return responseJson;
	}
	
	
	@RequestMapping(value = "/updateParentDetails/v1", method = RequestMethod.POST)
	public String updateParentDetails(@RequestBody String requestJson) {
		System.out.println("Start updateParentDetails ParentController");
		ParentDto parentDto = new ParentDto();
		String responseJson = null;
		ParentResponseDto parentResponseDto = new ParentResponseDto();
		List<ParentDto> parentDtoList = new ArrayList<ParentDto>();

		System.out.println("updateParentDetails RequestJson in ParentController: " + requestJson);
		try {

			parentDto = (ParentDto) jsonTransformers.JsonToObject(requestJson, parentDto);
			parentService.updateParentDetails(parentDto);

			parentDtoList = parentService.getParentDetails();
			System.out.println("ParentList in TaskController :"+parentDtoList.size());
			
			if(parentDtoList != null){
				for(ParentDto parent : parentDtoList){
					String taskName = parent.getParent_task();
					System.out.println("ParentName: "+taskName);
				}
			}

			ResponseHeaderDto responseHeader = new ResponseHeaderDto();
			responseHeader.setStatus(RESPONSE_SUCCESS);
			responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);

			if (parentDtoList.size() > 0) {
				parentResponseDto.setParentInfo(parentDtoList);
				responseHeader.setResponseMessage(RESPONSE_MESSAGE);
			} else {
				responseHeader.setResponseMessage("Parent does not exist");
			}
			parentResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(parentResponseDto);
			System.out.println("updateParentDetails ResponseJson in ParentController: " + responseJson);

			System.out.println("End updateParentDetails ParentController");
		} catch (Exception e) {
		}

		return responseJson;
	}
	
	
	
	@RequestMapping(value = "/deleteParentDetails/v1", method = RequestMethod.POST)
	public String deleteParentDetails(@RequestBody String requestJson) {
		System.out.println("Start deleteParentDetails ParentController");
		ParentDto parentDto = new ParentDto();
		String responseJson = null;
		ParentResponseDto parentResponseDto = new ParentResponseDto();
		List<ParentDto> parentDtoList = new ArrayList<ParentDto>();

		System.out.println("deleteParentDetails RequestJson in ParentController: " + requestJson);
		try {

			parentDto = (ParentDto) jsonTransformers.JsonToObject(requestJson, parentDto);
			parentService.deleteParentDetails(parentDto.getParent_id());

			parentDtoList = parentService.getParentDetails();
			System.out.println("ParentList after delete: " + parentDtoList.size());

			ResponseHeaderDto responseHeader = new ResponseHeaderDto();
			responseHeader.setStatus(RESPONSE_SUCCESS);
			responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);

			if (parentDtoList.size() > 0) {
				responseHeader.setResponseMessage(RESPONSE_MESSAGE);
				parentResponseDto.setParentInfo(parentDtoList);
			} else {
				responseHeader.setResponseMessage("Task does not exist");
			}

			parentResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(parentResponseDto);
			System.out.println("deleteParentDetails ResponseJson in ParentController: " + responseJson);

			System.out.println("End deleteParentDetails ParentController");
		} catch (Exception e) {
		}

		return responseJson;
	}
	
	
	
	@RequestMapping(value = "/addParentInfo/v1", method = RequestMethod.POST)
	public String addParentInfo(@RequestBody String requestJson) {
		System.out.println("Start addParentInfo ParentController");

		ParentDto parentDto = new ParentDto();
		String responseJson = null;
		ParentResponseDto parentResponseDto = new ParentResponseDto();
		List<ParentDto> parentDtoList = new ArrayList<ParentDto>();
		ResponseHeaderDto responseHeader = new ResponseHeaderDto();


		try {
			System.out.println("addParentInfo RequestJson in ParentController: " + requestJson);
			parentDto = (ParentDto) jsonTransformers.JsonToObject(requestJson, parentDto);
			System.out.println("ParentName from addParentInfo: " + parentDto.getParent_task());

			parentService.addParentInfo(parentDto);
			System.out.println("TaskInfo Saved successfully.");

			parentDtoList = parentService.getParentDetails();

			if (parentDtoList.size() > 0) {
				responseHeader.setResponseMessage(RESPONSE_MESSAGE);
				responseHeader.setStatus(RESPONSE_SUCCESS);
				responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);
				parentResponseDto.setParentInfo(parentDtoList);
			} else {
				responseHeader.setResponseMessage("Parent is empty in response");

			}
			parentResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(parentResponseDto);
			System.out.println("addParentInfo responseJson in ParentController: " + responseJson);

			System.out.println("End addParentInfo ParentController");
		} catch (Exception e) {

		}

		return responseJson;
	}

	
}
