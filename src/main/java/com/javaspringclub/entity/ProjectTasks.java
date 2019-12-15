package com.javaspringclub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProjectTasks {
	@Id
	@Column(name="noOfTasks")
	private int noOfTasks;

	/**
	 * @return the noOfTasks
	 */
	public int getNoOfTasks() {
		return noOfTasks;
	}

	/**
	 * @param noOfTasks the noOfTasks to set
	 */
	public void setNoOfTasks(int noOfTasks) {
		this.noOfTasks = noOfTasks;
	}

	
	
}
