package com.vh.manchester.bean;

import java.io.Serializable;
import java.sql.Date;

/**
 * The VH Corporation
 *
 * Copyright (c) 2003 The VH Corporation.  All rights reserved.  
 * Copying or reproduction without prior written approval is prohibited.
 * 
 * @author peter.szocs
 * May 28, 2004
 */
public class DateBean implements Serializable {

	private String  desc;
	private int value;
	

	/**
	 * Constructor.
	 */
	public DateBean(String desc, int value) {
		this.desc = desc;
		this.value = value;
	}


	/**
	 * @return desc
	 */
	public String getDesc() {
		return desc;
	}


	/**
	 * @param string
	 */
	public void setDesc(String string) {
		desc = string;
	}

	/**
	 * @return
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param i
	 */
	public void setValue(int i) {
		value = i;
	}

}
