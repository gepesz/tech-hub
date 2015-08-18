/**
 * @file SessionUtils
 * @author peter.szocs
 * @version 1.0
 * 
 * Session var utils.
 */


package com.vh.manchester.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.vh.manchester.bean.DateBean;
import com.vh.manchester.ejb.Locale;
import com.vh.manchester.ejb.Project;
import com.vh.manchester.ejb.User;
import com.vh.manchester.service.Service;

/**
 * The VH Corporation
 *
 * Copyright (c) 2003 The VH Corporation.  All rights reserved.  
 * Copying or reproduction without prior written approval is prohibited.
 * 
 */
public class SessionUtils {
	
  private static Logger log = Logger.getLogger(SessionUtils.class);
  private static final boolean IS_DEBUG = log.isDebugEnabled();


  /**
   * Puts the Throwable object into session, and returns
   * 'error' global ActionForward (see struts-config.xml, global forwards).
   * 
   * @param HttpServletRequest
   * @param Throwable
   * @return ActionForward under key 'error'
   */
  public static ActionForward handleThrowable(HttpServletRequest req, Throwable e, ActionMapping mapping) {
    setThrowable(req, e);
    return mapping.findForward("error");
  }


  /**
   * Puts the Throwable object into session.
   * 
   * @param HttpServletRequest
   * @param Throwable
   */
  public static void setThrowable(HttpServletRequest req, Throwable e) {
    req.getSession().setAttribute(Constants_Scope.GLOBALERRORS_KEY, e);
  }


  /**
   * Returns the Throwable object from session.
   * 
   * @param HttpServletRequest
   * @return Throwable
   */
  public static Throwable getThrowable(HttpServletRequest req) {
    return (Throwable) req.getSession().getAttribute(Constants_Scope.GLOBALERRORS_KEY);
  }


  /**
   * Checks whether the session has expired.
   * 
   * @param HttpServletRequest
   * @return boolean session status
   */
  public static boolean sessionExpired(HttpServletRequest req) {
    return (getUserFromSession(req)==null);
  }


  /**
   * Returns the required service from application scope.
   * 
   * @param String svcName The required service's name
   * @param HttpServletRequest request
   * @return Service
   */
  public static Service getService(String svcName, HttpServletRequest req) {
    return (Service) req.getSession().getServletContext().getAttribute(svcName);
  }


  /**
   * Returns the userId from session.
   * 
   * @param HttpServletRequest
   * @return Integer the userId
   */
  public static Integer getUserId(HttpServletRequest req) {
    return getUserFromSession(req).getId();
  }


  /**
   * Returns the User from session.
   * 
   * @param HttpServletRequest
   * @return User the authenticated user
   */
  public static User getUserFromSession(HttpServletRequest req) {
    return (User) req.getSession().getAttribute(Constants_Scope.USER_KEY);
  }


  /**
   * Puts the User into session.
   * 
   * @param HttpServletRequest
   * @param User the authenticated user
   */
  public static void setUserIntoSession(HttpServletRequest req, User usr) {
    req.getSession().setAttribute(Constants_Scope.USER_KEY, usr);
  }


  /**
   * Returns the projectId from session.
   * 
   * @param HttpServletRequest
   * @return Integer the projectId
   */
  public static Integer getProjectId(HttpServletRequest req) {
	return getProjectFromSession(req).getId();
  }


  /**
   * Returns the Project from session.
   * 
   * @param HttpServletRequest
   * @return Project the current project
   */
  public static Project getProjectFromSession(HttpServletRequest req) {
	return (Project) req.getSession().getAttribute(Constants_Scope.PROJECT_KEY);
  }


  /**
   * Puts the Project into session.
   * 
   * @param HttpServletRequest
   * @param Project the project
   */
  public static void setProjectIntoSession(HttpServletRequest req, Project proj) {
	req.getSession().setAttribute(Constants_Scope.PROJECT_KEY, proj);
  }


  /**
   * Returns the filterform from session.
   * 
   * @param HttpServletRequest
   * @return DynaValidatorForm the filterform
   */
  public static DynaValidatorForm getFiltersFromSession(HttpServletRequest req) {
	return (DynaValidatorForm) req.getSession().getAttribute(Constants_Scope.FILTERFORM_KEY);
  }


  /**
   * Puts the filterform into session.
   * 
   * @param HttpServletRequest
   * @param DynaValidatorForm the filterform
   */
  public static void setFiltersIntoSession(HttpServletRequest req, DynaValidatorForm filterform) {
	req.getSession().setAttribute(Constants_Scope.FILTERFORM_KEY, filterform);
  }


  /**
   * Returns the last taskform from session.
   * 
   * @param HttpServletRequest
   * @return DynaValidatorForm the taskform
   */
  public static DynaValidatorForm getTaskFormFromSession(HttpServletRequest req) {
	return (DynaValidatorForm) req.getSession().getAttribute(Constants_Scope.LASTTASKFORM_KEY);
  }


  /**
   * Puts the taskform into session.
   * 
   * @param HttpServletRequest
   * @param DynaValidatorForm the taskform
   */
  public static void setTaskFormIntoSession(HttpServletRequest req, DynaValidatorForm taskform) {
	req.getSession().setAttribute(Constants_Scope.LASTTASKFORM_KEY, taskform);
  }


  /**
   * Returns the accessId from session.
   * 
   * @param HttpServletRequest
   * @return Integer the accessId
   */
  public static Integer getAccessId(HttpServletRequest req) {
	return (Integer) req.getSession().getAttribute(Constants_Scope.ACCESS_KEY);
  }


  /**
   * Puts the accessId into session.
   * 
   * @param HttpServletRequest
   * @param Integer the accessId
   */
  public static void setProjectAccessIdIntoSession(HttpServletRequest req, Integer accessId) {
	req.getSession().setAttribute(Constants_Scope.ACCESS_KEY, accessId);
  }


  /**
   * Returns the developers collection from session.
   * 
   * @param HttpServletRequest
   * @return Collection the collection of developers in the current project
   */
  public static Collection getDevelopersForProject(HttpServletRequest req) {
	return (Collection) req.getSession().getAttribute(Constants_Scope.ALL_DEVELOPERS_IN_PROJECT_KEY);
  }


  /**
   * Puts the developers collection into session.
   * 
   * @param HttpServletRequest
   * @param Collection the collection of developers
   */
  public static void setDevelopersForProjectIntoSession(HttpServletRequest req, Collection developerColl) {
	req.getSession().setAttribute(Constants_Scope.ALL_DEVELOPERS_IN_PROJECT_KEY,  developerColl);
  }

}
