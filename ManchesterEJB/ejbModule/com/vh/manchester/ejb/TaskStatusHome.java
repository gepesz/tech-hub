package com.vh.manchester.ejb;
/**
 * Local Home interface for Enterprise Bean: TaskStatus
 */
public interface TaskStatusHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: TaskStatus
	 */
	public com.vh.manchester.ejb.TaskStatus create(java.lang.Integer id, java.lang.String desc) throws javax.ejb.CreateException;

	/**
	 * Finds an instance using a key for Entity Bean: TaskStatus
	 */
	public com.vh.manchester.ejb.TaskStatus findByPrimaryKey(java.lang.Integer primaryKey) throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
}
