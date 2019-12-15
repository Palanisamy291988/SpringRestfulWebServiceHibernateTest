package com.javaspringclub.entity;

import java.util.List;

public class UserResponseDto {
	
	private ResponseHeaderDto responseHeader;
	private List<UserDto> userInfo;
	/**
	 * @return the responseHeader
	 */
	public ResponseHeaderDto getResponseHeader() {
		return responseHeader;
	}
	/**
	 * @param responseHeader the responseHeader to set
	 */
	public void setResponseHeader(ResponseHeaderDto responseHeader) {
		this.responseHeader = responseHeader;
	}
	/**
	 * @return the userInfo
	 */
	public List<UserDto> getUserInfo() {
		return userInfo;
	}
	/**
	 * @param userInfo the userInfo to set
	 */
	public void setUserInfo(List<UserDto> userInfo) {
		this.userInfo = userInfo;
	}
	
	
}
