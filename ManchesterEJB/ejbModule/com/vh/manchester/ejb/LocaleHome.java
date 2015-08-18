package com.vh.manchester.ejb;
/**
 * Local Home interface for Enterprise Bean: Locale
 */
public interface LocaleHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Locale
	 */
	public com.vh.manchester.ejb.Locale create(java.lang.Integer id, java.lang.String desc, java.lang.String loc) throws javax.ejb.CreateException;

	/**
	 * Finds an instance using a key for Entity Bean: Locale
	 */
	public com.vh.manchester.ejb.Locale findByPrimaryKey(java.lang.Integer primaryKey) throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
}
