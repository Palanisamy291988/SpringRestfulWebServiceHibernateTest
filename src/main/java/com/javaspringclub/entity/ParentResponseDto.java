package com.javaspringclub.entity;

import java.util.List;

public class ParentResponseDto {
	
	private ResponseHeaderDto responseHeader;
	private List<ParentDto> parentInfo;
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
	 * @return the parentInfo
	 */
	public List<ParentDto> getParentInfo() {
		return parentInfo;
	}
	/**
	 * @param parentInfo the parentInfo to set
	 */
	public void setParentInfo(List<ParentDto> parentInfo) {
		this.parentInfo = parentInfo;
	}
	
	
	
}
