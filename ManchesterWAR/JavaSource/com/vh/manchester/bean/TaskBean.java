package com.vh.manchester.bean;

import java.sql.Date;

/**
 * The VH Corporation
 *
 * Copyright (c) 2003 The VH Corporation.  All rights reserved.  
 * Copying or reproduction without prior written approval is prohibited.
 * 
 * @author peter.szocs
 * Nov 5, 2003
 */
public class TaskBean {

	private Integer id;
	private Integer type;
	private String title;
	private String summary;
	private Date plannedDate;
	private String priority;
	private String status;
	private String difficulty;
	private String who;
	private int progress;
	
	

	/**
	 * Returns the difficulty.
	 * @return String
	 */
	public String getDifficulty() {
		return difficulty;
	}

	/**
	 * Returns the id.
	 * @return Integer
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Returns the plannedDate.
	 * @return Date
	 */
	public Date getPlannedDate() {
		return plannedDate;
	}

	/**
	 * Returns the priority.
	 * @return String
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * Returns the progress.
	 * @return int
	 */
	public int getProgress() {
		return progress;
	}

	/**
	 * Returns the status.
	 * @return String
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Returns the title.
	 * @return String
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the type.
	 * @return Integer
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * Returns the who.
	 * @return String
	 */
	public String getWho() {
		return who;
	}

	/**
	 * Sets the difficulty.
	 * @param difficulty The difficulty to set
	 */
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * Sets the id.
	 * @param id The id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Sets the plannedDate.
	 * @param plannedDate The plannedDate to set
	 */
	public void setPlannedDate(Date plannedDate) {
		this.plannedDate = plannedDate;
	}

	/**
	 * Sets the priority.
	 * @param priority The priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	/**
	 * Sets the progress.
	 * @param progress The progress to set
	 */
	public void setProgress(int progress) {
		this.progress = progress;
	}

	/**
	 * Sets the status.
	 * @param status The status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Sets the title.
	 * @param title The title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Sets the type.
	 * @param type The type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * Sets the who.
	 * @param who The who to set
	 */
	public void setWho(String who) {
		this.who = who;
	}

	/**
	 * Returns the summary.
	 * @return summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * Sets the summary.
	 * @param string the summary
	 */
	public void setSummary(String string) {
		summary = string;
	}

}
