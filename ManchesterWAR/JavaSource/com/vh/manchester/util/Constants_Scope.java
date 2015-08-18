/**
 * @file Constants_Scope
 * @author peter.szocs
 * 
 * Constants that are used in any of the scopes: 
 *      request, session, application.
 */


package com.vh.manchester.util;

/**
 * The VH Corporation
 *
 * Copyright (c) 2003 The VH Corporation.  All rights reserved.  
 * Copying or reproduction without prior written approval is prohibited.
 * 
 * @author  peter.szocs
 * @version 1.0
 */
public interface Constants_Scope {

  //REQUEST keys: keys for objects in request scope
  public static final String LOCALE_CHANGED_KEY              = "com.vh.manchester.LOCALE_CHANGED";
  public static final String NO_ACCESS_TO_PROJECT_KEY        = "com.vh.manchester.NO_ACCESS_TO_PROJECT";
  public static final String PROJECTS_CHANGED                = "com.vh.manchester.PROJECTS_CHANGED";
  public static final String TASK_KEY                        = "com.vh.manchester.TASK";
  public static final String PROJECT_BEING_UPDATED_KEY       = "com.vh.manchester.PROJECT_BEING_UPDATED";
  public static final String TASKFORM_KEY                    = "taskform";
  public static final String TASKCOLL_KEY                    = "com.vh.manchester.TASKCOLL";
  public static final String EDITSETTINGS_KEY                = "com.vh.manchester.EDITSETTINGS";
  public static final String MONITORCOLL_KEY                 = "com.vh.manchester.MONITORCOLL";
  public static final String PROJECTACCESSCOLL_KEY           = "com.vh.manchester.PROJECTACCESSCOLL";


  //SESSION keys: keys for objects in session scope
  public static final String USER_KEY                        = "com.vh.manchester.USER";
  public static final String FILTERFORM_KEY                  = "com.vh.manchester.FILTERFORM";
  public static final String LASTTASKFORM_KEY                = "com.vh.manchester.LASTTASKFORM";
  public static final String PROJECT_KEY                     = "com.vh.manchester.PROJECT";
  public static final String ACCESS_KEY                      = "com.vh.manchester.ACCESSID";
  public static final String ALL_DEVELOPERS_IN_PROJECT_KEY   = "com.vh.manchester.ALL_DEVELOPERS_IN_PROJECT";
  public static final String GLOBALERRORS_KEY                = "com.vh.manchester.GLOBALERRORS";
  

  //APPLICATION scope keys: keys for objects in application scope
  public static final String MANCHESTERSVC_KEY         = "com.vh.manchester.MANCHESTERSVC";
  public static final String LABELS_KEY                = "org.apache.struts.action.MESSAGE";
  public static final String SSOENABLED_KEY            = "com.vh.manchester.SSOENABLED";
  public static final String ALL_TASKTYPES_KEY         = "com.vh.manchester.ALLTASKTYPES";
  public static final String ALL_TASKPRIORITIES_KEY    = "com.vh.manchester.ALLTASKPRIORITIES";
  public static final String ALL_TASKDIFFICULTIES_KEY  = "com.vh.manchester.ALLTASKDIFFICULTIES";
  public static final String ALL_TASKSTATUSES_KEY      = "com.vh.manchester.ALLTASKSTATUSES";
  public static final String ALL_DATES_KEY             = "com.vh.manchester.ALLDATES";
  public static final String ALL_LOCALES_KEY           = "com.vh.manchester.ALLLOCALES";
  public static final String ALL_USERTYPES_KEY         = "com.vh.manchester.ALLUSERTYPES";
  public static final String ALL_ACCESSES_KEY          = "com.vh.manchester.ALLACCESSES";
  public static final String ALL_PROJECTS_KEY          = "com.vh.manchester.ALLPROJECTS";
  public static final String ALL_USERS_KEY             = "com.vh.manchester.ALLUSERS";
  
}