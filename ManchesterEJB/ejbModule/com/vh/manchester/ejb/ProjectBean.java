package com.vh.manchester.ejb;
/**
 * Bean implementation class for Enterprise Bean: Project
 */
public abstract class ProjectBean implements javax.ejb.EntityBean {
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
	public java.lang.Integer ejbCreate(java.lang.Integer id, java.lang.String desc)
		throws javax.ejb.CreateException {
		setId(id);
		setDesc(desc);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Integer id, java.lang.String desc)
		throws javax.ejb.CreateException {
	}
	
	public boolean update(java.lang.String desc) {
		boolean updated = false;
		try {
			if((desc!=null) && !desc.equals(getDesc())) setDesc(desc);
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
	 * Get accessor for persistent attribute: desc
	 */
	public abstract java.lang.String getDesc();
	/**
	 * Set accessor for persistent attribute: desc
	 */
	public abstract void setDesc(java.lang.String newDesc);
	/**
	 * This method was generated for supporting the relationship role named task.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getTask();
	/**
	 * This method was generated for supporting the relationship role named task.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setTask(java.util.Collection aTask);
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
	 * ejbCreate
	 */
	public java.lang.Integer ejbCreate(java.lang.Integer id)
		throws javax.ejb.CreateException {
		setId(id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Integer id)
		throws javax.ejb.CreateException {
	}
	/**
	 * This method was generated for supporting the relationship role named user.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getUser();
	/**
	 * This method was generated for supporting the relationship role named user.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setUser(java.util.Collection anUser);
}
