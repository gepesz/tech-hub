package com.vh.manchester.ejb;
/**
 * Local interface for Enterprise Bean: User
 */
public interface User extends javax.ejb.EJBLocalObject {

	public boolean update(java.lang.String username, java.lang.String password, java.lang.String email, com.vh.manchester.ejb.UserType aUserType, com.vh.manchester.ejb.Locale aLocale);

	public boolean updateLastProject(com.vh.manchester.ejb.Project aLastProject);

	/**
	 * Get accessor for persistent attribute: id
	 */
	public abstract java.lang.Integer getId();
	/**
	 * Get accessor for persistent attribute: username
	 */
	public java.lang.String getUsername();
	/**
	 * Set accessor for persistent attribute: username
	 */
	public void setUsername(java.lang.String newUsername);
	/**
	 * Get accessor for persistent attribute: password
	 */
	public java.lang.String getPassword();
	/**
	 * Set accessor for persistent attribute: password
	 */
	public void setPassword(java.lang.String newPassword);
	/**
	 * Get accessor for persistent attribute: email
	 */
	public java.lang.String getEmail();
	/**
	 * Set accessor for persistent attribute: email
	 */
	public void setEmail(java.lang.String newEmail);
	/**
	 * Get accessor for persistent attribute: lastLogin
	 */
	public java.sql.Timestamp getLastLogin();
	/**
	 * Set accessor for persistent attribute: lastLogin
	 */
	public void setLastLogin(java.sql.Timestamp newLastLogin);
	/**
	 * Get accessor for persistent attribute: nofLogins
	 */
	public int getNofLogins();
	/**
	 * Set accessor for persistent attribute: nofLogins
	 */
	public void setNofLogins(int newNofLogins);
	/**
	 * This method was generated for supporting the relationship role named locale.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public com.vh.manchester.ejb.Locale getLocale();
	/**
	 * This method was generated for supporting the relationship role named locale.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setLocale(com.vh.manchester.ejb.Locale aLocale);
	/**
	 * This method was generated for supporting the relationship role named userType.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public com.vh.manchester.ejb.UserType getUserType();
	/**
	 * This method was generated for supporting the relationship role named userType.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setUserType(com.vh.manchester.ejb.UserType anUserType);
	/**
	 * This method was generated for supporting the relationship role named assignedTasks.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getAssignedTasks();
	/**
	 * This method was generated for supporting the relationship role named assignedTasks.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setAssignedTasks(java.util.Collection anAssignedTasks);
	/**
	 * This method was generated for supporting the relationship role named createdTasks.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getCreatedTasks();
	/**
	 * This method was generated for supporting the relationship role named createdTasks.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setCreatedTasks(java.util.Collection aCreatedTasks);
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
	 * This method was generated for supporting the relationship role named lastProject.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public com.vh.manchester.ejb.Project getLastProject();
	/**
	 * This method was generated for supporting the relationship role named lastProject.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setLastProject(com.vh.manchester.ejb.Project aLastProject);
}
