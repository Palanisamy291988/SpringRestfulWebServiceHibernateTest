package com.javaspringclub.entity;

import java.util.List;

public class TaskResponseDto {
	
	private ResponseHeaderDto responseHeader;
	private List<TaskDto> taskInfo;
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
	 * @return the taskInfo
	 */
	public List<TaskDto> getTaskInfo() {
		return taskInfo;
	}
	/**
	 * @param taskInfo the taskInfo to set
	 */
	public void setTaskInfo(List<TaskDto> taskInfo) {
		this.taskInfo = taskInfo;
	}
	
	
}
