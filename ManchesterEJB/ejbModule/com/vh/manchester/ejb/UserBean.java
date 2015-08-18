package com.vh.manchester.ejb;
/**
 * Bean implementation class for Enterprise Bean: User
 */
public abstract class UserBean implements javax.ejb.EntityBean {
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
	public java.lang.Integer ejbCreate(java.lang.Integer id, java.lang.String username, java.lang.String password, java.lang.String email, java.sql.Timestamp lastLogin, int nofLogins, com.vh.manchester.ejb.Locale aLocale, com.vh.manchester.ejb.UserType anUserType, com.vh.manchester.ejb.Project aLastProject)
		throws javax.ejb.CreateException {
		setId(id);
		setUsername(username);
		setPassword(password);
		setEmail(email);
		setLastLogin(lastLogin);
		setNofLogins(nofLogins);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Integer id, java.lang.String username, java.lang.String password, java.lang.String email, java.sql.Timestamp lastLogin, int nofLogins, com.vh.manchester.ejb.Locale aLocale, com.vh.manchester.ejb.UserType anUserType, com.vh.manchester.ejb.Project aLastProject)
		throws javax.ejb.CreateException {
		setLocale(aLocale);
		setUserType(anUserType);
		setLastProject(aLastProject);
	}
	
	public boolean update(java.lang.String username, java.lang.String password, java.lang.String email, com.vh.manchester.ejb.UserType aUserType, com.vh.manchester.ejb.Locale aLocale) {
		
		try {
			if(!username.equals(getUsername())) 	setUsername(username);
			if(!password.equals(getPassword())) 	setPassword(password);
			if(!email.equals(getEmail())) 			setEmail(email);
			if(!aUserType.equals(getUserType())) 	setUserType(aUserType);
			if(!aLocale.equals(getLocale())) 		setLocale(aLocale);

		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;		
	}

	public boolean updateLastProject(com.vh.manchester.ejb.Project aLastProject) {
		try {
			if((aLastProject!=null) && (!aLastProject.equals(getLastProject()))) {
				setLastProject(aLastProject);
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;				
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
	 * Get accessor for persistent attribute: username
	 */
	public abstract java.lang.String getUsername();
	/**
	 * Set accessor for persistent attribute: username
	 */
	public abstract void setUsername(java.lang.String newUsername);
	/**
	 * Get accessor for persistent attribute: password
	 */
	public abstract java.lang.String getPassword();
	/**
	 * Set accessor for persistent attribute: password
	 */
	public abstract void setPassword(java.lang.String newPassword);
	/**
	 * Get accessor for persistent attribute: email
	 */
	public abstract java.lang.String getEmail();
	/**
	 * Set accessor for persistent attribute: email
	 */
	public abstract void setEmail(java.lang.String newEmail);
	/**
	 * Get accessor for persistent attribute: lastLogin
	 */
	public abstract java.sql.Timestamp getLastLogin();
	/**
	 * Set accessor for persistent attribute: lastLogin
	 */
	public abstract void setLastLogin(java.sql.Timestamp newLastLogin);
	/**
	 * Get accessor for persistent attribute: nofLogins
	 */
	public abstract int getNofLogins();
	/**
	 * Set accessor for persistent attribute: nofLogins
	 */
	public abstract void setNofLogins(int newNofLogins);
	/**
	 * This method was generated for supporting the relationship role named locale.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract com.vh.manchester.ejb.Locale getLocale();
	/**
	 * This method was generated for supporting the relationship role named locale.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setLocale(com.vh.manchester.ejb.Locale aLocale);
	/**
	 * This method was generated for supporting the relationship role named userType.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract com.vh.manchester.ejb.UserType getUserType();
	/**
	 * This method was generated for supporting the relationship role named userType.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setUserType(com.vh.manchester.ejb.UserType anUserType);
	/**
	 * This method was generated for supporting the relationship role named assignedTasks.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getAssignedTasks();
	/**
	 * This method was generated for supporting the relationship role named assignedTasks.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setAssignedTasks(java.util.Collection anAssignedTasks);
	/**
	 * This method was generated for supporting the relationship role named createdTasks.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getCreatedTasks();
	/**
	 * This method was generated for supporting the relationship role named createdTasks.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCreatedTasks(java.util.Collection aCreatedTasks);
	/**
	 * This method was generated for supporting the relationship role named projectAccess.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getProjectAccess();
	/**
	 * This method was generated for supporting the relationship role named projectAccess.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setProjectAccess(java.util.Collection aProjectAccess);
	/**
	 * This method was generated for supporting the relationship role named lastProject.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract com.vh.manchester.ejb.Project getLastProject();
	/**
	 * This method was generated for supporting the relationship role named lastProject.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setLastProject(com.vh.manchester.ejb.Project aLastProject);
}
