package com.vh.manchester.ejb;
/**
 * Local Home interface for Enterprise Bean: Priority
 */
public interface PriorityHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Priority
	 */
	public com.vh.manchester.ejb.Priority create(java.lang.Integer id, java.lang.String desc) throws javax.ejb.CreateException;

	/**
	 * Finds an instance using a key for Entity Bean: Priority
	 */
	public com.vh.manchester.ejb.Priority findByPrimaryKey(java.lang.Integer primaryKey) throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
}
