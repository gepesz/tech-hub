package com.vh.manchester.ejb;
/**
 * Local Home interface for Enterprise Bean: ProjectAccess
 */
public interface ProjectAccessHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: ProjectAccess
	 */
	public com.vh.manchester.ejb.ProjectAccess create(java.lang.Integer projectId, java.lang.Integer userId, com.vh.manchester.ejb.Access anAccess) throws javax.ejb.CreateException;

	/**
	 * Finds an instance using a key for Entity Bean: ProjectAccess
	 */
	public com.vh.manchester.ejb.ProjectAccess findByPrimaryKey(com.vh.manchester.ejb.ProjectAccessKey primaryKey) throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
	public java.util.Collection findByProject(java.lang.Integer projectId) throws javax.ejb.FinderException;
	public java.util.Collection findByUser(java.lang.Integer userId) throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: ProjectAccess
	 */
	public com.vh.manchester.ejb.ProjectAccess create(
		java.lang.Integer projectId,
		java.lang.Integer userId)
		throws javax.ejb.CreateException;
}
