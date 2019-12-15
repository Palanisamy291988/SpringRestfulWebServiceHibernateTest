package com.javaspringclub.entity;

import java.util.List;

public class ProjectResponseDto {
	
	private ResponseHeaderDto responseHeader;
	private List<Project> projectInfo;
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
	 * @return the projectInfo
	 */
	public List<Project> getProjectInfo() {
		return projectInfo;
	}
	/**
	 * @param projectInfo the projectInfo to set
	 */
	public void setProjectInfo(List<Project> projectInfo) {
		this.projectInfo = projectInfo;
	}
	
}
