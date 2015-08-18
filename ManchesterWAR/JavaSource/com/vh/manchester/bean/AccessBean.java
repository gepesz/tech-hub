package com.vh.manchester.bean;

import java.sql.Timestamp;

/**
 * The VH Corporation
 *
 * Copyright (c) 2003 The VH Corporation.  All rights reserved.  
 * Copying or reproduction without prior written approval is prohibited.
 * 
 * @author peter.szocs
 * Apr 24, 2004
 */
public class AccessBean {

	private Integer userId;
	private boolean hasAccess;
	private String userName;
	private String email;
	private Integer accessId;
	
	
	

	/**
	 * Constructor for AccessBean.
	 */
	public AccessBean() {
		this.userId=null;
		this.hasAccess=false;
		this.userName=null;
		this.email=null;
		this.accessId=null;
	}


	/**
	 * @return
	 */
	public Integer getAccessId() {
		return accessId;
	}

	/**
	 * @return
	 */
	public boolean isHasAccess() {
		return hasAccess;
	}

	/**
	 * @return
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param integer
	 */
	public void setAccessId(Integer integer) {
		accessId = integer;
	}

	/**
	 * @param b
	 */
	public void setHasAccess(boolean b) {
		hasAccess = b;
	}

	/**
	 * @param integer
	 */
	public void setUserId(Integer integer) {
		userId = integer;
	}

	/**
	 * @param string
	 */
	public void setUserName(String string) {
		userName = string;
	}

	/**
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param string
	 */
	public void setEmail(String string) {
		email = string;
	}

}
