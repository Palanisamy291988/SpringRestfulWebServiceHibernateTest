package com.javaspringclub.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaspringclub.entity.UserDto;
import com.javaspringclub.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userkRepository;
	

	
	@Override
	@Transactional
	public List<UserDto> getUserDetails() {
		List<UserDto> list = new ArrayList<UserDto>();
		userkRepository.findAll().forEach(e -> list.add(e));
		return list;
	}



	@Override
	@Transactional
	public UserDto getUserDetailsById(int user_id) {
		UserDto user = userkRepository.findById(user_id).get();
		return user;
	}



	@Override
	@Transactional
	public void updateUserDetails(UserDto userDto) {
		UserDto user = userkRepository.findById(userDto.getUser_id()).get();
		System.out.println("EmployeeIDFromDb : "+userDto.getEmployee_id());
		
		if(user != null){
			userkRepository.save(userDto);
			System.out.println("User updated successfully : "+user.getEmployee_id());
		}
		
	}



	@Override
	@Transactional
	public void deleteUserDetails(int user_id) {
		userkRepository.deleteById(user_id);
	}



	@Override
	public void addUserInfo(UserDto userDto) {
		System.out.println("addUserInfo is called in UserServiceImpl");
		userkRepository.save(userDto);
		System.out.println("User Details saved successfully.");
		
	}

	

}
