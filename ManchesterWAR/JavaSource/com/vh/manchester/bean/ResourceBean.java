/**
 * @file ResourceBean
 * @author peter.szocs
 * @version 1.0
 * 
 * Bean to hold message resources parameter and key.
 */


package com.vh.manchester.bean;

/**
 * The VH Corporation
 *
 * Copyright (c) 2003 The VH Corporation.  All rights reserved.  
 * Copying or reproduction without prior written approval is prohibited.
 * 
 * @author  peter.szocs
 * @version 1.0
 */
public class ResourceBean {

	private String parameter;
	private String key;

	public ResourceBean(String parameter, String key) {
		this.parameter=parameter;
		this.key=key;
	}
	
	/**
	 * Returns the key.
	 * @return String
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Returns the parameter.
	 * @return String
	 */
	public String getParameter() {
		return parameter;
	}

	/**
	 * Sets the key.
	 * @param key The key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Sets the parameter.
	 * @param parameter The parameter to set
	 */
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

}
