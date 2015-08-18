/**
 * @file ActionUtils
 * @author peter.szocs
 * @version 1.0
 * 
 * Contains utility methods needed by Struts Actions.
 */


package com.vh.manchester.util;


import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * The VH Corporation
 *
 * Copyright (c) 2003 The VH Corporation.  All rights reserved.  
 * Copying or reproduction without prior written approval is prohibited.
 * 
 */
public class ActionUtils {
	
  private static Logger log = Logger.getLogger(ActionUtils.class);
  private static final boolean IS_DEBUG = log.isDebugEnabled();


  /**
   * Returns today's date.
   */
  public static Date today() {
	  return (new java.sql.Date(System.currentTimeMillis()));
  }


  /**
   * Returns the current timestamp.
   */
  public static Timestamp rightNow() {
	  return (new java.sql.Timestamp(System.currentTimeMillis()));
  }


  /**
   * Converts a com.vh.manchester.ejb.Locale object into a java.util.Locale object.
   * The method takes in our Locale object as parameter and returns the equivalent
   * java.util.Locale object. 
   * 
   * @param aLocale  the Locale to convert
   * @return         the converted Locale object
   */
  public static java.util.Locale convertLocale(com.vh.manchester.ejb.Locale aLocale) {
	  String language = aLocale.getLoc().substring(0, 2);
	  String country  = aLocale.getLoc().substring(3);
	  java.util.Locale loc = new java.util.Locale(language, country);
	  return loc;
  }

}
