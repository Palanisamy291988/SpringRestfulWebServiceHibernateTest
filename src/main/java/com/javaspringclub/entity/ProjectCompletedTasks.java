package com.javaspringclub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProjectCompletedTasks {
	@Id
	@Column(name="completedTasks")
	private int completedTasks;

	/**
	 * @return the completedTasks
	 */
	public int getCompletedTasks() {
		return completedTasks;
	}

	/**
	 * @param completedTasks the completedTasks to set
	 */
	public void setCompletedTasks(int completedTasks) {
		this.completedTasks = completedTasks;
	}

	

	
	
}
