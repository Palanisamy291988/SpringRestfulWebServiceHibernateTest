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
import com.javaspringclub.entity.UserDto;
import com.javaspringclub.entity.UserResponseDto;
import com.javaspringclub.helper.JsonTransformers;
import com.javaspringclub.repository.UserRepository;
import com.javaspringclub.service.UserService;

@RestController
public class UserController extends RestConstants {

	static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	UserRepository userRepository;

	JsonTransformers jsonTransformers = new JsonTransformers();

	public UserController() {

	}

	public UserController(UserService userService) {
		this.userService = userService;
	}

	

	@RequestMapping(value = "/getUserDetails/v1", method = RequestMethod.POST)
	public String getUserDetails() {
		System.out.println("Start getUserDetails UserController");
		String responseJson = null;

		try {

			ResponseHeaderDto responseHeader = new ResponseHeaderDto();
			responseHeader.setStatus(RESPONSE_SUCCESS);
			responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);
			responseHeader.setResponseMessage(RESPONSE_MESSAGE);

			UserResponseDto userResponseDto = new UserResponseDto();
			List<UserDto> userList = userService.getUserDetails();
			System.out.println("userList from getUserDetails: " + userList + "taskList size: " + userList.size());
			userResponseDto.setUserInfo(userList);
			userResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(userResponseDto);
			System.out.println("getUserDetails ResponseJson in UserController: " + responseJson);

			System.out.println("End getUserDetails UserController");
		} catch (Exception e) {
		}

		return responseJson;
	}
	
	
	
	
	@RequestMapping(value = "/getUserDetailsById/v1", method = RequestMethod.POST)
	public String getUserDetailsById(@RequestBody String requestJson) {
		System.out.println("Start getUserDetailsById UserController");
		logger.info("Start getUserDetailsById UserController");
		UserDto userDto = new UserDto();
		String responseJson = null;
		UserResponseDto userResponseDto = new UserResponseDto();
		List<UserDto> userDtoList = null;

		System.out.println("getUserDetailsById RequestJson in UserController: " + requestJson);
		logger.debug("getUserDetailsById RequestJson in UserController: " + requestJson);
		try {

			JsonTransformers jsonTransformers = new JsonTransformers();
			userDto = (UserDto) jsonTransformers.JsonToObject(requestJson, userDto);
			userDto = userService.getUserDetailsById(userDto.getUser_id());

			ResponseHeaderDto responseHeader = new ResponseHeaderDto();
			responseHeader.setStatus(RESPONSE_SUCCESS);
			responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);

			if (userDto != null) {
				userDtoList = new ArrayList<UserDto>();
				userDtoList.add(userDto);
				responseHeader.setResponseMessage(RESPONSE_MESSAGE);
			} else {
				responseHeader.setResponseMessage("User does not exist");
			}

			userResponseDto.setUserInfo(userDtoList);
			userResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(userResponseDto);
			System.out.println("getUserDetailsById ResponseJson in UserController: " + responseJson);

			System.out.println("End getUserDetailsById UserController");
		} catch (Exception e) {
		}

		return responseJson;
	}
	
	
	@RequestMapping(value = "/updateUserDetails/v1", method = RequestMethod.POST)
	public String updateUserDetails(@RequestBody String requestJson) {
		System.out.println("Start updateUserDetails UserController");
		UserDto userDto = new UserDto();
		String responseJson = null;
		UserResponseDto userResponseDto = new UserResponseDto();
		List<UserDto> userDtoList = new ArrayList<UserDto>();

		System.out.println("updateUserDetails RequestJson in UserController: " + requestJson);
		try {

			userDto = (UserDto) jsonTransformers.JsonToObject(requestJson, userDto);
			userService.updateUserDetails(userDto);

			userDtoList = userService.getUserDetails();
			System.out.println("UserList in TaskController :"+userDtoList.size());
			
			if(userDtoList != null){
				for(UserDto user : userDtoList){
					String userId = user.getEmployee_id();
					System.out.println("UserID: "+userId);
				}
			}

			ResponseHeaderDto responseHeader = new ResponseHeaderDto();
			responseHeader.setStatus(RESPONSE_SUCCESS);
			responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);

			if (userDtoList.size() > 0) {
				userResponseDto.setUserInfo(userDtoList);
				responseHeader.setResponseMessage(RESPONSE_MESSAGE);
			} else {
				responseHeader.setResponseMessage("User does not exist");
			}
			userResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(userResponseDto);
			System.out.println("updateUserDetails ResponseJson in UserController: " + responseJson);

			System.out.println("End updateUserDetails UserController");
		} catch (Exception e) {
		}

		return responseJson;
	}
	
	
	
	@RequestMapping(value = "/deleteUserDetails/v1", method = RequestMethod.POST)
	public String deleteUserDetails(@RequestBody String requestJson) {
		System.out.println("Start deleteUserDetails UserController");
		UserDto userDto = new UserDto();
		String responseJson = null;
		UserResponseDto userResponseDto = new UserResponseDto();
		List<UserDto> userDtoList = new ArrayList<UserDto>();

		System.out.println("deleteUserDetails RequestJson in UserController: " + requestJson);
		try {

			userDto = (UserDto) jsonTransformers.JsonToObject(requestJson, userDto);
		    userService.deleteUserDetails(userDto.getUser_id());

			userDtoList = userService.getUserDetails();
			System.out.println("UserList after delete: " + userDtoList.size());

			ResponseHeaderDto responseHeader = new ResponseHeaderDto();
			responseHeader.setStatus(RESPONSE_SUCCESS);
			responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);

			if (userDtoList.size() > 0) {
				responseHeader.setResponseMessage(RESPONSE_MESSAGE);
				userResponseDto.setUserInfo(userDtoList);
			} else {
				responseHeader.setResponseMessage("User does not exist");
			}

			userResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(userResponseDto);
			System.out.println("deleteUserDetails ResponseJson in UserController: " + responseJson);

			System.out.println("End deleteUserDetails UserController");
		} catch (Exception e) {
		}

		return responseJson;
	}
	
	
	//Add User Details
	
	
	@RequestMapping(value = "/addUserInfo/v1", method = RequestMethod.POST)
	public String addUserInfo(@RequestBody String requestJson) {
		System.out.println("Start addUserInfo in UserController");

		UserDto userDto = new UserDto();
		String responseJson = null;
		UserResponseDto userResponseDto = new UserResponseDto();
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		ResponseHeaderDto responseHeader = new ResponseHeaderDto();

		try {
			System.out.println("addUserInfo RequestJson in UserController: " + requestJson);
			userDto = (UserDto) jsonTransformers.JsonToObject(requestJson, userDto);
			System.out.println("EmployeeID from addProjectInfo: " + userDto.getEmployee_id());

			userService.addUserInfo(userDto);
			System.out.println("UserInfo Saved successfully.");

			userDtoList = userService.getUserDetails();

			if (userDtoList.size() > 0) {
				
				responseHeader.setResponseMessage(RESPONSE_MESSAGE);
				responseHeader.setStatus(RESPONSE_SUCCESS);
				responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);
				
				userResponseDto.setUserInfo(userDtoList);
			} else {
				responseHeader.setResponseMessage("User is empty in response");

			}
			userResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(userResponseDto);
			System.out.println("addUserInfo responseJson in UserController: " + responseJson);

			System.out.println("End addUserInfo UserController");
		} catch (Exception e) {

		}

		return responseJson;
	}
	
	
	// Sort User By FirstName
	@RequestMapping(value = "/getSortUserByFirstName/v1", method = RequestMethod.POST)
	public String getSortUserByFirstName() {
		System.out.println("Start getSortUserByFirstName UserController");
		String responseJson = null;

		try {

			ResponseHeaderDto responseHeader = new ResponseHeaderDto();
			responseHeader.setStatus(RESPONSE_SUCCESS);
			responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);
			responseHeader.setResponseMessage(RESPONSE_MESSAGE);

			UserResponseDto userResponseDto = new UserResponseDto();
			List<UserDto> userList = userRepository.getSortUserByFirstName();
			System.out.println("userList from getSortUserByFirstName: " + userList + "userList size: " + userList.size());
			userResponseDto.setUserInfo(userList);
			userResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(userResponseDto);
			System.out.println("getSortUserByFirstName ResponseJson in UserController: " + responseJson);

			System.out.println("End getSortUserByFirstName UserController");
		} catch (Exception e) {
		}

		return responseJson;
	}
	
	// Sort User By LastName
	
	@RequestMapping(value = "/getSortUserByLastName/v1", method = RequestMethod.POST)
	public String getSortUserByLastName() {
		System.out.println("Start getSortUserByLastName UserController");
		String responseJson = null;

		try {

			ResponseHeaderDto responseHeader = new ResponseHeaderDto();
			responseHeader.setStatus(RESPONSE_SUCCESS);
			responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);
			responseHeader.setResponseMessage(RESPONSE_MESSAGE);

			UserResponseDto userResponseDto = new UserResponseDto();
			List<UserDto> userList = userRepository.getSortUserByLastName();
			System.out.println("userList from getUserDetails: " + userList + "userList size: " + userList.size());
			userResponseDto.setUserInfo(userList);
			userResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(userResponseDto);
			System.out.println("getSortUserByLastName ResponseJson in UserController: " + responseJson);

			System.out.println("End getSortUserByLastName UserController");
		} catch (Exception e) {
		}

		return responseJson;
	}
	
	// Sort User By EmployeeId
	
	@RequestMapping(value = "/getSortUserByEmployeeId/v1", method = RequestMethod.POST)
	public String getSortUserByEmployeeId() {
		System.out.println("Start getSortUserByEmployeeId UserController");
		String responseJson = null;

		try {

			ResponseHeaderDto responseHeader = new ResponseHeaderDto();
			responseHeader.setStatus(RESPONSE_SUCCESS);
			responseHeader.setResponseCode(RESPONSE_CODE_SUCCESS);
			responseHeader.setResponseMessage(RESPONSE_MESSAGE);

			UserResponseDto userResponseDto = new UserResponseDto();
			List<UserDto> userList = userRepository.getSortUserByEmployeeId();
			System.out.println("userList from getSortUserByEmployeeId: " + userList + "userList size: " + userList.size());
			userResponseDto.setUserInfo(userList);
			userResponseDto.setResponseHeader(responseHeader);

			responseJson = jsonTransformers.ObjectToJson(userResponseDto);
			System.out.println("getSortUserByEmployeeId ResponseJson in UserController: " + responseJson);

			System.out.println("End getSortUserByEmployeeId UserController");
		} catch (Exception e) {
		}

		return responseJson;
	}
	
	
}
