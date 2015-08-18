package com.vh.manchester.ejb;
/**
 * Local Home interface for Enterprise Bean: Project
 */
public interface ProjectHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Project
	 */
	public com.vh.manchester.ejb.Project create(java.lang.Integer id, java.lang.String desc) throws javax.ejb.CreateException;

	/**
	 * Finds an instance using a key for Entity Bean: Project
	 */
	public com.vh.manchester.ejb.Project findByPrimaryKey(java.lang.Integer primaryKey) throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Project
	 */
	public com.vh.manchester.ejb.Project create(java.lang.Integer id)
		throws javax.ejb.CreateException;
}
