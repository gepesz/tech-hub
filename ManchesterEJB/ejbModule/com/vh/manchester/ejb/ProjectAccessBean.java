package com.vh.manchester.ejb;
/**
 * Bean implementation class for Enterprise Bean: ProjectAccess
 */
public abstract class ProjectAccessBean implements javax.ejb.EntityBean {
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
	public com.vh.manchester.ejb.ProjectAccessKey ejbCreate(java.lang.Integer projectId, java.lang.Integer userId, com.vh.manchester.ejb.Access anAccess) throws javax.ejb.CreateException {
		setProjectId(projectId);
		setUserId(userId);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Integer projectId, java.lang.Integer userId, com.vh.manchester.ejb.Access anAccess) throws javax.ejb.CreateException {
		setAccess(anAccess);
	}
	
	public boolean update(com.vh.manchester.ejb.Access anAccess) {
		try {
			if(anAccess!=null) {			
				if(!anAccess.equals(getAccess())) {
					setAccess(anAccess);
				}
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
	 * Get accessor for persistent attribute: projectId
	 */
	public abstract java.lang.Integer getProjectId();
	/**
	 * Set accessor for persistent attribute: projectId
	 */
	public abstract void setProjectId(java.lang.Integer newProjectId);
	/**
	 * Get accessor for persistent attribute: userId
	 */
	public abstract java.lang.Integer getUserId();
	/**
	 * Set accessor for persistent attribute: userId
	 */
	public abstract void setUserId(java.lang.Integer newUserId);
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
	/**
	 * This method was generated for supporting the relationship role named user.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract com.vh.manchester.ejb.User getUser();
	/**
	 * This method was generated for supporting the relationship role named user.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setUser(com.vh.manchester.ejb.User anUser);
	/**
	 * This method was generated for supporting the relationship role named access.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract com.vh.manchester.ejb.Access getAccess();
	/**
	 * This method was generated for supporting the relationship role named access.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setAccess(com.vh.manchester.ejb.Access anAccess);
	/**
	 * ejbCreate
	 */
	public com.vh.manchester.ejb.ProjectAccessKey ejbCreate(
		java.lang.Integer projectId,
		java.lang.Integer userId)
		throws javax.ejb.CreateException {
		setProjectId(projectId);
		setUserId(userId);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Integer projectId,
		java.lang.Integer userId)
		throws javax.ejb.CreateException {
	}
}
