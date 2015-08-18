package com.vh.manchester.ejb;
/**
 * Local Home interface for Enterprise Bean: TaskType
 */
public interface TaskTypeHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: TaskType
	 */
	public com.vh.manchester.ejb.TaskType create(java.lang.Integer id, java.lang.String desc) throws javax.ejb.CreateException;

	/**
	 * Finds an instance using a key for Entity Bean: TaskType
	 */
	public com.vh.manchester.ejb.TaskType findByPrimaryKey(java.lang.Integer primaryKey) throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
}
