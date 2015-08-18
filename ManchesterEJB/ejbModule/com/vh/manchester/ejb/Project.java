package com.vh.manchester.ejb;
/**
 * Local interface for Enterprise Bean: Project
 */
public interface Project extends javax.ejb.EJBLocalObject {

	public boolean update(java.lang.String desc);

	/**
	 * Get accessor for persistent attribute: id
	 */
	public java.lang.Integer getId();
	/**
	 * Get accessor for persistent attribute: desc
	 */
	public java.lang.String getDesc();
	/**
	 * Set accessor for persistent attribute: desc
	 */
	public void setDesc(java.lang.String newDesc);
	/**
	 * This method was generated for supporting the relationship role named task.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getTask();
	/**
	 * This method was generated for supporting the relationship role named task.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setTask(java.util.Collection aTask);
	/**
	 * This method was generated for supporting the relationship role named projectAccess.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getProjectAccess();
	/**
	 * This method was generated for supporting the relationship role named projectAccess.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setProjectAccess(java.util.Collection aProjectAccess);
	/**
	 * This method was generated for supporting the relationship role named user.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getUser();
	/**
	 * This method was generated for supporting the relationship role named user.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setUser(java.util.Collection anUser);
}
