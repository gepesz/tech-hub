package com.vh.manchester.ejb;
/**
 * Local Home interface for Enterprise Bean: UserType
 */
public interface UserTypeHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: UserType
	 */
	public com.vh.manchester.ejb.UserType create(java.lang.Integer id, java.lang.String desc) throws javax.ejb.CreateException;

	/**
	 * Finds an instance using a key for Entity Bean: UserType
	 */
	public com.vh.manchester.ejb.UserType findByPrimaryKey(java.lang.Integer primaryKey) throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: UserType
	 */
	public com.vh.manchester.ejb.UserType create(java.lang.Integer id)
		throws javax.ejb.CreateException;
}
