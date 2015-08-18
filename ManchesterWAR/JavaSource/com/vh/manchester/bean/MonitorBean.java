package com.vh.manchester.bean;

import java.sql.Timestamp;

/**
 * The VH Corporation
 *
 * Copyright (c) 2003 The VH Corporation.  All rights reserved.  
 * Copying or reproduction without prior written approval is prohibited.
 * 
 * @author peter.szocs
 * Nov 5, 2003
 */
public class MonitorBean {

	private Integer userId;
	private String userName;
	private String email;
	private Timestamp lastLogin;
	private double avgOpenProgress;
	private int nofLogins;
	private int nofOpen;
	private int nofRslvd;
	private int nofOther;
	private int nofTotal;
	
	


	/**
	 * Constructor for MonitorBean.
	 */
	public MonitorBean() {
		this.avgOpenProgress = 0;
		this.nofLogins	= 0;
		this.nofOpen	= 0;
		this.nofOther	= 0;
		this.nofRslvd	= 0;
		this.nofTotal	= 0;
	}

	/**
	 * Returns the avgOpenProgress.
	 * @return double
	 */
	public double getAvgOpenProgress() {
		return avgOpenProgress;
	}

	/**
	 * Returns the email.
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Returns the lastLogin.
	 * @return Timestamp
	 */
	public Timestamp getLastLogin() {
		return lastLogin;
	}

	/**
	 * Returns the nofLogins.
	 * @return int
	 */
	public int getNofLogins() {
		return nofLogins;
	}

	/**
	 * Returns the nofOpen.
	 * @return int
	 */
	public int getNofOpen() {
		return nofOpen;
	}

	/**
	 * Returns the nofOther.
	 * @return int
	 */
	public int getNofOther() {
		return nofOther;
	}

	/**
	 * Returns the nofRslvd.
	 * @return int
	 */
	public int getNofRslvd() {
		return nofRslvd;
	}

	/**
	 * Returns the nofTotal.
	 * @return int
	 */
	public int getNofTotal() {
		return nofTotal;
	}

	/**
	 * Returns the userName.
	 * @return String
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the avgOpenProgress.
	 * @param avgOpenProgress The avgOpenProgress to set
	 */
	public void setAvgOpenProgress(double avgOpenProgress) {
		this.avgOpenProgress = avgOpenProgress;
	}

	/**
	 * Sets the email.
	 * @param email The email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets the lastLogin.
	 * @param lastLogin The lastLogin to set
	 */
	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * Sets the nofLogins.
	 * @param nofLogins The nofLogins to set
	 */
	public void setNofLogins(int nofLogins) {
		this.nofLogins = nofLogins;
	}

	/**
	 * Sets the nofOpen.
	 * @param nofOpen The nofOpen to set
	 */
	public void setNofOpen(int nofOpen) {
		this.nofOpen = nofOpen;
	}

	/**
	 * Sets the nofOther.
	 * @param nofOther The nofOther to set
	 */
	public void setNofOther(int nofOther) {
		this.nofOther = nofOther;
	}

	/**
	 * Sets the nofRslvd.
	 * @param nofRslvd The nofRslvd to set
	 */
	public void setNofRslvd(int nofRslvd) {
		this.nofRslvd = nofRslvd;
	}

	/**
	 * Sets the nofTotal.
	 * @param nofTotal The nofTotal to set
	 */
	public void setNofTotal(int nofTotal) {
		this.nofTotal = nofTotal;
	}

	/**
	 * Sets the userName.
	 * @param userName The userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Returns the userId.
	 * @return Integer
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * Sets the userId.
	 * @param userId The userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
