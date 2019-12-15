package com.javaspringclub.entity;

import java.util.List;

public class ProjectInfoResponseDto {
	
	private ResponseHeaderDto responseHeader;
	private List<ProjectDto> projectInfo;
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
	public List<ProjectDto> getProjectInfo() {
		return projectInfo;
	}
	/**
	 * @param projectInfo the projectInfo to set
	 */
	public void setProjectInfo(List<ProjectDto> projectInfo) {
		this.projectInfo = projectInfo;
	}
	
}
