package com.vh.manchester.ejb;
/**
 * Key class for Entity Bean: ProjectAccess
 */
public class ProjectAccessKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: projectId
	 */
	public java.lang.Integer projectId;
	/**
	 * Implementation field for persistent attribute: userId
	 */
	public java.lang.Integer userId;
	/**
	 * Creates an empty key for Entity Bean: ProjectAccess
	 */
	public ProjectAccessKey() {
	}
	/**
	 * Creates a key for Entity Bean: ProjectAccess
	 */
	public ProjectAccessKey(
		java.lang.Integer projectId,
		java.lang.Integer userId) {
		this.projectId = projectId;
		this.userId = userId;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof com.vh.manchester.ejb.ProjectAccessKey) {
			com.vh.manchester.ejb.ProjectAccessKey o =
				(com.vh.manchester.ejb.ProjectAccessKey) otherKey;
			return (
				(this.projectId.equals(o.projectId))
					&& (this.userId.equals(o.userId)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (projectId.hashCode() + userId.hashCode());
	}
}
