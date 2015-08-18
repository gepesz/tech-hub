/**
 * @file RequestUtils
 * @author peter.szocs
 * @version 1.0
 * 
 * Request var utils.
 */


package com.vh.manchester.util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.validator.DynaValidatorForm;

import com.vh.manchester.ejb.Project;
import com.vh.manchester.ejb.Task;


/**
 * The VH Corporation
 *
 * Copyright (c) 2003 The VH Corporation.  All rights reserved.  
 * Copying or reproduction without prior written approval is prohibited.
 * 
 */
public class RequestUtils {
	
  private static Logger log = Logger.getLogger(RequestUtils.class);
  private static final boolean IS_DEBUG = log.isDebugEnabled();



  /**
   * Puts the task into request for edittask.jsp to use it.
   * 
   * @param HttpServletRequest
   * @param Task the task
   */
  public static void setTask(HttpServletRequest req, Task task) {
	req.setAttribute(Constants_Scope.TASK_KEY, task);
  }


  /**
   * Puts the project into request for editproject.jsp to use it.
   * 
   * @param HttpServletRequest
   * @param Project the project
   */
  public static void setProject(HttpServletRequest req, Project proj) {
	req.setAttribute(Constants_Scope.PROJECT_BEING_UPDATED_KEY, proj);
  }


  /**
   * Puts the taskform into request.
   * 
   * @param HttpServletRequest
   * @param DynaValidatorForm the taskform
   */
  public static void setTaskForm(HttpServletRequest req, DynaValidatorForm taskform) {
	req.setAttribute(Constants_Scope.TASKFORM_KEY, taskform);
  }


  /**
   * Puts the taskColl into request.  This is the collection for viewtasks.jsp to use it.
   * 
   * @param HttpServletRequest
   * @param Collection the task collection
   */
  public static void setTaskColl(HttpServletRequest req, Collection taskColl) {
	req.setAttribute(Constants_Scope.TASKCOLL_KEY, taskColl);
  }


  /**
   * Puts the monitor beans (mbeans) collection into request for monitor.jsp to use it.
   * 
   * @param HttpServletRequest
   * @param Collection the mbean collection
   */
  public static void setMonitorColl(HttpServletRequest req, Collection monitorColl) {
	req.setAttribute(Constants_Scope.MONITORCOLL_KEY, monitorColl);
  }


  /**
   * Puts the project access beans (abeans) collection into request for access.jsp to use it.
   * 
   * @param HttpServletRequest
   * @param Collection the abean collection
   */
  public static void setProjectAccessColl(HttpServletRequest req, Collection projAccessColl) {
	req.setAttribute(Constants_Scope.PROJECTACCESSCOLL_KEY, projAccessColl);
  }


  /**
   * Sets a true (integer 1) into request for viewtasks.jsp to use it when the locale changed
   * right after an editsettings operation.
   * 
   * @param HttpServletRequest
   */
  public static void setLocaleChanged(HttpServletRequest req) {
	req.setAttribute(Constants_Scope.LOCALE_CHANGED_KEY, new Integer(1));
  }


  /**
   * Sets an integer into request for viewprojects.jsp to use it.
   * 
   * @param HttpServletRequest
   * @param int value the value, based on what changed: 1-add, 2-update, 3-delete
   */
  public static void setProjectsChanged(HttpServletRequest req, int value) {
	req.setAttribute(Constants_Scope.PROJECTS_CHANGED, new Integer(value));
  }


  /**
   * Sets a true (integer 1) into request for viewtasks.jsp to use it when the user has no
   * access to a project.
   * 
   * @param HttpServletRequest
   */
  public static void setNoProjectAccess(HttpServletRequest req) {
	req.setAttribute(Constants_Scope.NO_ACCESS_TO_PROJECT_KEY, new Integer(1));
  }


  /**
   * Puts 0/1 into request to tell edituser.jsp whether this request is coming from
   * the Settings link XOR an admin is editing users.  1 means coming from Settings link.
   * 
   * @param HttpServletRequest
   * @param int
   */
  public static void setEditSettings(HttpServletRequest req, Integer my) {
	req.setAttribute(Constants_Scope.EDITSETTINGS_KEY, my);
  }

}