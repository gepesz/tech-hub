package com.vh.manchester.ejb;
/**
 * Local Home interface for Enterprise Bean: User
 */
public interface UserHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: User
	 */
	public com.vh.manchester.ejb.User create(java.lang.Integer id, java.lang.String username, java.lang.String password, java.lang.String email, java.sql.Timestamp lastLogin, int nofLogins, com.vh.manchester.ejb.Locale aLocale, com.vh.manchester.ejb.UserType anUserType, com.vh.manchester.ejb.Project aLastProject) throws javax.ejb.CreateException;

	/**
	 * Finds an instance using a key for Entity Bean: User
	 */
	public com.vh.manchester.ejb.User findByPrimaryKey(java.lang.Integer primaryKey) throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
	public com.vh.manchester.ejb.User findByUsername(java.lang.String username) throws javax.ejb.FinderException;
	public java.util.Collection findByProject(java.lang.Integer projectId)
		throws javax.ejb.FinderException;
	public java.util.Collection findDevelopersByProject(
		java.lang.Integer projectId)
		throws javax.ejb.FinderException;
}
