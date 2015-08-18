/**
 * @file Constants
 * @author peter.szocs
 * 
 * Constants to be used throughout the Manchester project.
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
public interface Constants {

  //SMTP server for email
  public static final String SMTPServer = "mail.speakeasy.net";
  public static final boolean EMAIL_ENABLED=true;
  

  //Hash passwords or not
  public static final boolean HASH_PASSWORD = true;


  //Default user/project ids
  public static final Integer DEFAULT_USER_ID    = new Integer(1);
  public static final Integer DEFAULT_PROJECT_ID = new Integer(1);


  //TaskStatus
  public static final Integer TASKSTATUS_OPEN     = new Integer(1);
  public static final Integer TASKSTATUS_RSLVD    = new Integer(2);
  public static final Integer TASKSTATUS_VERIFIED = new Integer(3);


  //UserType
  public static final Integer USERTYPE_USER  = new Integer(1);
  public static final Integer USERTYPE_ADMIN = new Integer(2);


  //DataSource JNDI name
  //NOTE:(1) WebSphere: theDataSource = "jdbc/ManchesterDS_MySQL";
  //	 (2) JBOSS:     theDataSource = "java:/jdbc/ManchesterDS_MySQL";
  public static final String theDataSource = "java:/jdbc/ManchesterDS_MySQL";


  //EJB JNDI names
  public static final String Access_HOME_NAME=         "java:comp/env/com/vh/manchester/ejb/AccessHome";
  public static final String Difficulty_HOME_NAME=     "java:comp/env/com/vh/manchester/ejb/DifficultyHome";
  public static final String Locale_HOME_NAME=         "java:comp/env/com/vh/manchester/ejb/LocaleHome";
  public static final String Priority_HOME_NAME=       "java:comp/env/com/vh/manchester/ejb/PriorityHome";
  public static final String Project_HOME_NAME=        "java:comp/env/com/vh/manchester/ejb/ProjectHome";
  public static final String ProjectAccess_HOME_NAME=  "java:comp/env/com/vh/manchester/ejb/ProjectAccessHome";
  public static final String Task_HOME_NAME=           "java:comp/env/com/vh/manchester/ejb/TaskHome";
  public static final String TaskStatus_HOME_NAME=     "java:comp/env/com/vh/manchester/ejb/TaskStatusHome";
  public static final String TaskType_HOME_NAME=       "java:comp/env/com/vh/manchester/ejb/TaskTypeHome";
  public static final String User_HOME_NAME=           "java:comp/env/com/vh/manchester/ejb/UserHome";
  public static final String UserType_HOME_NAME=       "java:comp/env/com/vh/manchester/ejb/UserTypeHome";

}