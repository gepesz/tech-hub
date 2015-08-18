package com.vh.manchester.ejb;
/**
 * Local Home interface for Enterprise Bean: Access
 */
public interface AccessHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Access
	 */
	public com.vh.manchester.ejb.Access create(java.lang.Integer id, java.lang.String desc) throws javax.ejb.CreateException;

	/**
	 * Finds an instance using a key for Entity Bean: Access
	 */
	public com.vh.manchester.ejb.Access findByPrimaryKey(java.lang.Integer primaryKey) throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Access
	 */
	public com.vh.manchester.ejb.Access create(java.lang.Integer id)
		throws javax.ejb.CreateException;
}
