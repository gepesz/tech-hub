package com.vh.manchester.ejb;
/**
 * Bean implementation class for Enterprise Bean: Locale
 */
public abstract class LocaleBean implements javax.ejb.EntityBean {
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
	public java.lang.Integer ejbCreate(java.lang.Integer id, java.lang.String desc, java.lang.String loc)
		throws javax.ejb.CreateException {
		setId(id);
		setDesc(desc);
		setLoc(loc);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Integer id, java.lang.String desc, java.lang.String loc)
		throws javax.ejb.CreateException {
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
	 * This method was generated for supporting the relationship role named user.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getUser();
	/**
	 * This method was generated for supporting the relationship role named user.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setUser(java.util.Collection anUser);
	/**
	 * Get accessor for persistent attribute: loc
	 */
	public abstract java.lang.String getLoc();
	/**
	 * Set accessor for persistent attribute: loc
	 */
	public abstract void setLoc(java.lang.String newLoc);
}
