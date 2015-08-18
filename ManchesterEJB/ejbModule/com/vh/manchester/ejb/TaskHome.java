package com.vh.manchester.ejb;

import java.sql.Date;

/**
 * Local Home interface for Enterprise Bean: Task
 */
public interface TaskHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Task
	 */
	public com.vh.manchester.ejb.Task create(java.lang.Integer id, java.lang.String title, java.lang.String summary, Date plannedDate, Date createDate, java.lang.String comment, java.lang.Integer progress, com.vh.manchester.ejb.Difficulty aDifficulty, com.vh.manchester.ejb.TaskType aTaskType, com.vh.manchester.ejb.TaskStatus aTaskStatus, com.vh.manchester.ejb.Priority aPriority, com.vh.manchester.ejb.User assignedTo, com.vh.manchester.ejb.User createdBy, com.vh.manchester.ejb.Project project) throws javax.ejb.CreateException;

	/**
	 * Finds an instance using a key for Entity Bean: Task
	 */
	public com.vh.manchester.ejb.Task findByPrimaryKey(java.lang.Integer primaryKey) throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
	public java.util.Collection findByWho(java.lang.Integer userId) throws javax.ejb.FinderException;
	public java.util.Collection findByWhoOrCreatedBy(java.lang.Integer userId)
		throws javax.ejb.FinderException;
	public java.util.Collection findByProject(java.lang.Integer projectId) throws javax.ejb.FinderException;
}
