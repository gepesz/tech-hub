/**
 * @file MessageUtils
 * @author peter.szocs
 * @version 2.0
 * 
 * Utility class to access application.properties messages from Java files.
 */


package com.vh.manchester.util;


import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.log4j.Logger;
import org.apache.struts.Globals;
import org.apache.struts.util.MessageResources;

import com.vh.manchester.service.exception.MissingResourcesException;

/**
 * The VH Corporation
 *
 * Copyright (c) 2005 The VH Corporation.  All rights reserved.  
 * Copying or reproduction without prior written approval is prohibited.
 * 
 * @author  peter.szocs
 * @version 2.0
 */
public class MessageUtils {

  private static Logger log = Logger.getLogger(MessageUtils.class);

  private static Locale getLocale(HttpServletRequest request) {
    Locale locale = (Locale) Config.get(request.getSession(), Config.FMT_LOCALE);     //use this to get the current locale IFF <fmt:message key=... /> tags are used
    //Locale locale = (Locale) request.getSession().getAttribute(Globals.LOCALE_KEY); //use this to get the current locale IFF <b:message key=... /> tags are used
    if(locale==null) locale = Locale.getDefault();
    return locale;
  }

  private static Locale getLocale(String preferredLocale) {	
	Locale locale = new Locale(preferredLocale, "");
	if(locale==null) locale = Locale.getDefault();
	return locale;
  }


  //===========================================================
  // getMessage methods
  //===========================================================


  /**
   * This is the main getMessage method.  The others are wrappers around this.
   * Returns the message under the given bundle, locale, key, args.
   * 
   * @param bundle the bundle
   * @param key the msg key
   * @param args[] the arguments of the message
   * @param request the request
   * @param locale the locale 
   * @return String the message (null on error)
   */   
  private static String getMessageBase(String bundle, String key, Object args[], HttpServletRequest request, String locale) throws MissingResourcesException {
    String msg = "";
    try {
      MessageResources mr = (MessageResources) request.getSession().getServletContext().getAttribute(bundle);
      if(locale==null) msg = mr.getMessage(getLocale(request), key, args);
      else msg = mr.getMessage(getLocale(locale), key, args);
    } catch(Exception e) {
      log.error("Cannot find messages under bundle \""+bundle+"\", key \""+key+"\", locale \""+getLocale(request));
      throw new MissingResourcesException("Cannot find messages under bundle \""+bundle+"\", key \""+key+"\", locale \""+getLocale(request));      
    }
    return msg;
  }


  /**
   * Returns the message under the given bundle, locale, key.
   * The key has no input parameters.
   * 
   * @param bundle the bundle
   * @param key the msg key
   * @param request the request
   * @param locale the preferred locale
   * @return String the message (null on error)
   */
  public static String getMessage(String bundle, String key, HttpServletRequest request, String locale) throws MissingResourcesException {
	return getMessageBase(bundle, key, null, request, locale);
  }


  /**
   * Returns the message under the given bundle, locale, key.
   * The key has no input parameters.
   * 
   * @param bundle the bundle
   * @param key the msg key
   * @param request the request
   * @return String the message (null on error)
   */
  public static String getMessage(String bundle, String key, HttpServletRequest request) throws MissingResourcesException {
    return getMessageBase(bundle, key, null, request, null);
  }


  /**
   * Returns the message under the given bundle, locale, key.
   * Formats the message with input param arg0.
   * 
   * @param bundle the bundle
   * @param key the msg key
   * @param arg0 arg0
   * @param request the request
   * @return String the message (null on error)
   */
  public static String getMessage(String bundle, String key, Object arg0, HttpServletRequest request) throws MissingResourcesException {
    Object args[] = new Object[1];
    args[0] = arg0;
    return getMessageBase(bundle, key, args, request, null);
  }


  /**
   * Returns the message under the given bundle, locale, key.
   * Formats the message with input params arg0, arg1.
   * 
   * @param bundle the bundle
   * @param key the msg key
   * @param arg0 arg0
   * @param arg1 arg1
   * @param request the request
   * @return String the message (null on error)
   */
  public static String getMessage(String bundle, String key, Object arg0, Object arg1, HttpServletRequest request)  throws MissingResourcesException {
    Object args[] = new Object[2];
    args[0] = arg0;
    args[1] = arg1;
    return getMessageBase(bundle, key, args, request, null);
  }


  /**
   * Returns the message under the given bundle, locale, key.
   * Formats the message with input params arg0, arg1, arg2.
   * 
   * @param bundle the bundle
   * @param key the msg key
   * @param arg0 arg0
   * @param arg1 arg1
   * @param arg2 arg2
   * @param request the request
   * @return String the message (null on error)
   */
  public static String getMessage(String bundle, String key, Object arg0, Object arg1, Object arg2, HttpServletRequest request) throws MissingResourcesException {
    Object args[] = new Object[3];
    args[0] = arg0;
    args[1] = arg1;
    args[2] = arg2;
    return getMessageBase(bundle, key, args, request, null);
  }


  /**
   * Returns the message under the given bundle, locale, key.
   * Formats the message with input params arg0, arg1, arg2, arg3.
   * 
   * @param bundle the bundle
   * @param key the msg key
   * @param arg0 arg0
   * @param arg1 arg1
   * @param arg2 arg2
   * @param arg3 arg3
   * @param request the request
   * @return String the message (null on error)
   */
  public static String getMessage(String bundle, String key, Object arg0, Object arg1, Object arg2, Object arg3, HttpServletRequest request) throws MissingResourcesException {
    Object args[] = new Object[4];
    args[0] = arg0;
    args[1] = arg1;
    args[2] = arg2;
    args[3] = arg3;
    return getMessageBase(bundle, key, args, request, null);
  }


  /**
   * Returns the message under the given bundle, locale, key.
   * Formats the message with input params arg0, arg1, arg2, arg3, arg4.
   * 
   * @param bundle the bundle
   * @param key the msg key
   * @param arg0 arg0
   * @param arg1 arg1
   * @param arg2 arg2
   * @param arg3 arg3
   * @param arg4 arg4
   * @param request the request
   * @return String the message (null on error)
   */
  public static String getMessage(String bundle, String key, Object arg0, Object arg1, Object arg2, Object arg3, Object arg4, HttpServletRequest request) throws MissingResourcesException {
    Object args[] = new Object[5];
    args[0] = arg0;
    args[1] = arg1;
    args[2] = arg2;
    args[3] = arg3;
    args[4] = arg4;
    return getMessageBase(bundle, key, args, request, null);
  }

}