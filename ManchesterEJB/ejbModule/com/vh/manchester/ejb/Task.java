package com.vh.manchester.ejb;
/**
 * Local interface for Enterprise Bean: Task
 */
public interface Task extends javax.ejb.EJBLocalObject {

	public boolean update(java.lang.String title, java.lang.String summary, com.vh.manchester.ejb.TaskType aTaskType, com.vh.manchester.ejb.Priority aPriority, com.vh.manchester.ejb.Difficulty aDifficulty, com.vh.manchester.ejb.TaskStatus aTaskStatus, com.vh.manchester.ejb.Project aProject, com.vh.manchester.ejb.User assignedTo, com.vh.manchester.ejb.User createdBy, java.sql.Date plannedDate, java.lang.String comment, java.lang.Integer progress);

	/**
	 * Get accessor for persistent attribute: id
	 */
	public abstract java.lang.Integer getId();
	/**
	 * Get accessor for persistent attribute: title
	 */
	public java.lang.String getTitle();
	/**
	 * Set accessor for persistent attribute: title
	 */
	public void setTitle(java.lang.String newTitle);
	/**
	 * Get accessor for persistent attribute: summary
	 */
	public java.lang.String getSummary();
	/**
	 * Set accessor for persistent attribute: summary
	 */
	public void setSummary(java.lang.String newSummary);
	/**
	 * Get accessor for persistent attribute: plannedDate
	 */
	public java.sql.Date getPlannedDate();
	/**
	 * Set accessor for persistent attribute: plannedDate
	 */
	public void setPlannedDate(java.sql.Date newPlannedDate);
	/**
	 * Get accessor for persistent attribute: createDate
	 */
	public java.sql.Date getCreateDate();
	/**
	 * Set accessor for persistent attribute: createDate
	 */
	public void setCreateDate(java.sql.Date newCreateDate);
	/**
	 * Get accessor for persistent attribute: comment
	 */
	public java.lang.String getComment();
	/**
	 * Set accessor for persistent attribute: comment
	 */
	public void setComment(java.lang.String newComment);
	/**
	 * This method was generated for supporting the relationship role named difficulty.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public com.vh.manchester.ejb.Difficulty getDifficulty();
	/**
	 * This method was generated for supporting the relationship role named difficulty.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setDifficulty(com.vh.manchester.ejb.Difficulty aDifficulty);
	/**
	 * This method was generated for supporting the relationship role named taskType.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public com.vh.manchester.ejb.TaskType getTaskType();
	/**
	 * This method was generated for supporting the relationship role named taskType.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setTaskType(com.vh.manchester.ejb.TaskType aTaskType);
	/**
	 * This method was generated for supporting the relationship role named taskStatus.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public com.vh.manchester.ejb.TaskStatus getTaskStatus();
	/**
	 * This method was generated for supporting the relationship role named taskStatus.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setTaskStatus(com.vh.manchester.ejb.TaskStatus aTaskStatus);
	/**
	 * This method was generated for supporting the relationship role named priority.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public com.vh.manchester.ejb.Priority getPriority();
	/**
	 * This method was generated for supporting the relationship role named priority.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setPriority(com.vh.manchester.ejb.Priority aPriority);
	/**
	 * This method was generated for supporting the relationship role named who.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public com.vh.manchester.ejb.User getWho();
	/**
	 * This method was generated for supporting the relationship role named who.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setWho(com.vh.manchester.ejb.User aWho);
	/**
	 * This method was generated for supporting the relationship role named createdBy.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public com.vh.manchester.ejb.User getCreatedBy();
	/**
	 * This method was generated for supporting the relationship role named createdBy.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setCreatedBy(com.vh.manchester.ejb.User aCreatedBy);
	/**
	 * Get accessor for persistent attribute: progress
	 */
	public java.lang.Integer getProgress();
	/**
	 * Set accessor for persistent attribute: progress
	 */
	public void setProgress(java.lang.Integer newProgress);
	/**
	 * This method was generated for supporting the relationship role named project.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public com.vh.manchester.ejb.Project getProject();
	/**
	 * This method was generated for supporting the relationship role named project.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setProject(com.vh.manchester.ejb.Project aProject);
}
