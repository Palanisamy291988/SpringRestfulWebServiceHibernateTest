package com.javaspringclub.service;

import java.util.List;

import com.javaspringclub.entity.UserDto;

public interface UserService {

	public List<UserDto> getUserDetails();

	public UserDto getUserDetailsById(int user_id);

	public void updateUserDetails(UserDto userDto);

	public void deleteUserDetails(int user_id);

	public void addUserInfo(UserDto userDto);

}
