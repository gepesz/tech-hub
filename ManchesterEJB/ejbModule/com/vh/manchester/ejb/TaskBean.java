package com.vh.manchester.ejb;

import java.sql.Date;

/**
 * Bean implementation class for Enterprise Bean: Task
 */
public abstract class TaskBean implements javax.ejb.EntityBean {
	private javax.ejb.EntityContext myEntityCtx;
	/**
	 * setEntityContext
	 */
	public void setEntityContext(javax.ejb.EntityContext ctx) {
		myEntityCtx = ctx;
	}
	/**
	 * getEntityContext
	 */
	public javax.ejb.EntityContext getEntityContext() {
		return myEntityCtx;
	}
	/**
	 * unsetEntityContext
	 */
	public void unsetEntityContext() {
		myEntityCtx = null;
	}
	/**
	 * ejbCreate
	 */
	public java.lang.Integer ejbCreate(java.lang.Integer id, java.lang.String title, java.lang.String summary, Date plannedDate, Date createDate, java.lang.String comment, java.lang.Integer progress, com.vh.manchester.ejb.Difficulty aDifficulty, com.vh.manchester.ejb.TaskType aTaskType, com.vh.manchester.ejb.TaskStatus aTaskStatus, com.vh.manchester.ejb.Priority aPriority, com.vh.manchester.ejb.User assignedTo, com.vh.manchester.ejb.User createdBy, com.vh.manchester.ejb.Project project)
		throws javax.ejb.CreateException {
		setId(id);
		setTitle(title);
		setSummary(summary);
		setPlannedDate(plannedDate);
		setCreateDate(createDate);
		setComment(comment);
		setProgress(progress);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Integer id, java.lang.String title, java.lang.String summary, Date plannedDate, Date createDate, java.lang.String comment, java.lang.Integer progress, com.vh.manchester.ejb.Difficulty aDifficulty, com.vh.manchester.ejb.TaskType aTaskType, com.vh.manchester.ejb.TaskStatus aTaskStatus, com.vh.manchester.ejb.Priority aPriority, com.vh.manchester.ejb.User assignedTo, com.vh.manchester.ejb.User createdBy, com.vh.manchester.ejb.Project project)
		throws javax.ejb.CreateException {
		setDifficulty(aDifficulty);
		setTaskType(aTaskType);
		setTaskStatus(aTaskStatus);
		setPriority(aPriority);
		setWho(assignedTo);
		setCreatedBy(createdBy);	
		setProject(project);	
	}
	
	public boolean update(java.lang.String title, java.lang.String summary, com.vh.manchester.ejb.TaskType aTaskType, com.vh.manchester.ejb.Priority aPriority, com.vh.manchester.ejb.Difficulty aDifficulty, com.vh.manchester.ejb.TaskStatus aTaskStatus, com.vh.manchester.ejb.Project aProject, com.vh.manchester.ejb.User assignedTo, com.vh.manchester.ejb.User createdBy, java.sql.Date plannedDate, java.lang.String comment, java.lang.Integer progress) {

		boolean updated = false;
		try {
			if((title!=null) 		&& !title.equals(getTitle()))             setTitle(title);
			if((summary!=null) 		&& !summary.equals(getSummary()))         setSummary(summary);
			if((aTaskType!=null) 	&& !aTaskType.equals(getTaskType()))      setTaskType(aTaskType);
			if((aPriority!=null) 	&& !aPriority.equals(getPriority()))      setPriority(aPriority);
			if((aDifficulty!=null) 	&& !aDifficulty.equals(getDifficulty()))  setDifficulty(aDifficulty);
			if((aTaskStatus!=null) 	&& !aTaskStatus.equals(getTaskStatus()))  setTaskStatus(aTaskStatus);
			if((aProject!=null) 	&& !aProject.equals(getProject()))        setProject(aProject);
			if((assignedTo!=null) 	&& !assignedTo.equals(getWho())) 		  setWho(assignedTo);
			if((createdBy!=null) 	&& !createdBy.equals(getCreatedBy())) 	  setCreatedBy(createdBy);
			if((plannedDate!=null) 	&& !plannedDate.equals(getPlannedDate())) setPlannedDate(plannedDate);
			if((comment!=null) 		&& !comment.equals(getComment())) 	      setComment(comment);
			if((progress!=null) 	&& !progress.equals(getProgress()))       setProgress(progress);
			updated=true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return updated;
		
	}
	
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbLoad
	 */
	public void ejbLoad() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() throws javax.ejb.RemoveException {
	}
	/**
	 * ejbStore
	 */
	public void ejbStore() {
	}
	/**
	 * Get accessor for persistent attribute: id
	 */
	public abstract java.lang.Integer getId();
	/**
	 * Set accessor for persistent attribute: id
	 */
	public abstract void setId(java.lang.Integer newId);
	/**
	 * Get accessor for persistent attribute: title
	 */
	public abstract java.lang.String getTitle();
	/**
	 * Set accessor for persistent attribute: title
	 */
	public abstract void setTitle(java.lang.String newTitle);
	/**
	 * Get accessor for persistent attribute: summary
	 */
	public abstract java.lang.String getSummary();
	/**
	 * Set accessor for persistent attribute: summary
	 */
	public abstract void setSummary(java.lang.String newSummary);
	/**
	 * Get accessor for persistent attribute: plannedDate
	 */
	public abstract java.sql.Date getPlannedDate();
	/**
	 * Set accessor for persistent attribute: plannedDate
	 */
	public abstract void setPlannedDate(java.sql.Date newPlannedDate);
	/**
	 * Get accessor for persistent attribute: createDate
	 */
	public abstract java.sql.Date getCreateDate();
	/**
	 * Set accessor for persistent attribute: createDate
	 */
	public abstract void setCreateDate(java.sql.Date newCreateDate);
	/**
	 * Get accessor for persistent attribute: comment
	 */
	public abstract java.lang.String getComment();
	/**
	 * Set accessor for persistent attribute: comment
	 */
	public abstract void setComment(java.lang.String newComment);
	/**
	 * This method was generated for supporting the relationship role named difficulty.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract com.vh.manchester.ejb.Difficulty getDifficulty();
	/**
	 * This method was generated for supporting the relationship role named difficulty.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setDifficulty(com.vh.manchester.ejb.Difficulty aDifficulty);
	/**
	 * This method was generated for supporting the relationship role named taskType.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract com.vh.manchester.ejb.TaskType getTaskType();
	/**
	 * This method was generated for supporting the relationship role named taskType.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setTaskType(com.vh.manchester.ejb.TaskType aTaskType);
	/**
	 * This method was generated for supporting the relationship role named taskStatus.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract com.vh.manchester.ejb.TaskStatus getTaskStatus();
	/**
	 * This method was generated for supporting the relationship role named taskStatus.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setTaskStatus(com.vh.manchester.ejb.TaskStatus aTaskStatus);
	/**
	 * This method was generated for supporting the relationship role named priority.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract com.vh.manchester.ejb.Priority getPriority();
	/**
	 * This method was generated for supporting the relationship role named priority.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setPriority(com.vh.manchester.ejb.Priority aPriority);
	/**
	 * This method was generated for supporting the relationship role named who.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract com.vh.manchester.ejb.User getWho();
	/**
	 * This method was generated for supporting the relationship role named who.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setWho(com.vh.manchester.ejb.User aWho);
	/**
	 * This method was generated for supporting the relationship role named createdBy.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract com.vh.manchester.ejb.User getCreatedBy();
	/**
	 * This method was generated for supporting the relationship role named createdBy.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCreatedBy(com.vh.manchester.ejb.User aCreatedBy);
	/**
	 * Get accessor for persistent attribute: progress
	 */
	public abstract java.lang.Integer getProgress();
	/**
	 * Set accessor for persistent attribute: progress
	 */
	public abstract void setProgress(java.lang.Integer newProgress);
	/**
	 * This method was generated for supporting the relationship role named project.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract com.vh.manchester.ejb.Project getProject();
	/**
	 * This method was generated for supporting the relationship role named project.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setProject(com.vh.manchester.ejb.Project aProject);
}
