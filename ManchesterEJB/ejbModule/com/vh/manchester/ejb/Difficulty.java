package com.vh.manchester.ejb;
/**
 * Local interface for Enterprise Bean: Difficulty
 */
public interface Difficulty extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: id
	 */
	public abstract java.lang.Integer getId();
	/**
	 * Get accessor for persistent attribute: desc
	 */
	public java.lang.String getDesc();
	/**
	 * Set accessor for persistent attribute: desc
	 */
	public void setDesc(java.lang.String newDesc);
	/**
	 * This method was generated for supporting the relationship role named task.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getTask();
	/**
	 * This method was generated for supporting the relationship role named task.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setTask(java.util.Collection aTask);
}
