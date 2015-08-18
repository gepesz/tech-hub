package com.vh.manchester.ejb;
/**
 * Local interface for Enterprise Bean: ProjectAccess
 */
public interface ProjectAccess extends javax.ejb.EJBLocalObject {

	public boolean update(com.vh.manchester.ejb.Access anAccess);

	/**
	 * Get accessor for persistent attribute: projectId
	 */
	public java.lang.Integer getProjectId();
	/**
	 * Get accessor for persistent attribute: userId
	 */
	public java.lang.Integer getUserId();
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
	/**
	 * This method was generated for supporting the relationship role named user.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public com.vh.manchester.ejb.User getUser();
	/**
	 * This method was generated for supporting the relationship role named user.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setUser(com.vh.manchester.ejb.User anUser);
	/**
	 * This method was generated for supporting the relationship role named access.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public com.vh.manchester.ejb.Access getAccess();
	/**
	 * This method was generated for supporting the relationship role named access.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setAccess(com.vh.manchester.ejb.Access anAccess);
}
