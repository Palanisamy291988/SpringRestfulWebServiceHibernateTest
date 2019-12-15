package com.javaspringclub.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="parent")
public class ParentDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int parent_id;
	
	private String parent_task;

	/**
	 * @return the parent_id
	 */
	public int getParent_id() {
		return parent_id;
	}

	/**
	 * @param parent_id the parent_id to set
	 */
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	/**
	 * @return the parent_task
	 */
	public String getParent_task() {
		return parent_task;
	}

	/**
	 * @param parent_task the parent_task to set
	 */
	public void setParent_task(String parent_task) {
		this.parent_task = parent_task;
	}
   
	
}
