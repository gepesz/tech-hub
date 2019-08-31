/**
 * @file StartupServlet
 * @author peter.szocs
 * @version 1.0
 * 
 * This servlet is used to initialize parameters at server startup.
 */


package com.vh.manchester.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.Globals;

import com.vh.manchester.bean.DateBean;
import com.vh.manchester.ejb.Locale;
import com.vh.manchester.service.ManchesterService;
import com.vh.manchester.service.ManchesterServiceImpl_MySQL;
import com.vh.manchester.service.ManchesterServiceImpl_Postgre;
import com.vh.manchester.util.ActionUtils;
import com.vh.manchester.util.Constants;
import com.vh.manchester.util.Constants_Scope;
import com.vh.manchester.util.EJBHomeCache;


/**
 * The VH Corporation
 *
 * Copyright (c) 2003 The VH Corporation.  All rights reserved.  
 * Copying or reproduction without prior written approval is prohibited.
 * 
 */
public class StartupServlet extends HttpServlet implements Constants {
	
	private static Logger log=Logger.getLogger(StartupServlet.class);
	private static final boolean IS_DEBUG = log.isDebugEnabled();
	  
	public void service(HttpServletRequest req, HttpServletResponse  resp) throws ServletException, IOException {}



	private void cacheHomes() {
	  try {
		if(IS_DEBUG) log.debug("Caching EJB homes...");

		EJBHomeCache.lookup(Access_HOME_NAME);
		EJBHomeCache.lookup(Difficulty_HOME_NAME);
		EJBHomeCache.lookup(Locale_HOME_NAME);
		EJBHomeCache.lookup(Priority_HOME_NAME);
		EJBHomeCache.lookup(Project_HOME_NAME);
		EJBHomeCache.lookup(ProjectAccess_HOME_NAME);
		EJBHomeCache.lookup(Task_HOME_NAME);
		EJBHomeCache.lookup(TaskStatus_HOME_NAME);
		EJBHomeCache.lookup(TaskType_HOME_NAME);
		EJBHomeCache.lookup(User_HOME_NAME);
		EJBHomeCache.lookup(UserType_HOME_NAME);
	
		if(IS_DEBUG) log.debug("EJB homes cached");
	  } catch(Exception e) {
		log.error("Error while caching homes: "+e.toString());
		e.printStackTrace();
	  }		
	}



	private void initApplicationScope() {
	  try {
		if(IS_DEBUG) log.debug("Setting up application scope...");
		ServletContext context = this.getServletContext();
		ManchesterService man = new ManchesterServiceImpl_Postgre();
		context.setAttribute(Constants_Scope.MANCHESTERSVC_KEY,         man);
		//context.setAttribute(Constants_Scope.LABELS_KEY,              this.getResources(request));
		//context.setAttribute(Constants_Scope.SSOENABLED_KEY,          new Boolean(false));
		context.setAttribute(Constants_Scope.ALL_TASKTYPES_KEY,         man.findAllTaskType());
		context.setAttribute(Constants_Scope.ALL_TASKPRIORITIES_KEY,    man.findAllPriority());
		context.setAttribute(Constants_Scope.ALL_TASKDIFFICULTIES_KEY,  man.findAllDifficulty());
		context.setAttribute(Constants_Scope.ALL_TASKSTATUSES_KEY,      man.findAllTaskStatus());
		context.setAttribute(Constants_Scope.ALL_LOCALES_KEY,           man.findAllLocale());
		context.setAttribute(Constants_Scope.ALL_USERTYPES_KEY,         man.findAllUserType());
		context.setAttribute(Constants_Scope.ALL_ACCESSES_KEY,          man.findAllAccess());
		context.setAttribute(Constants_Scope.ALL_PROJECTS_KEY,          man.findAllProject());
		context.setAttribute(Constants_Scope.ALL_USERS_KEY,             man.findAllUser());
				
		ArrayList<DateBean> al = new ArrayList<DateBean>();
		al.add( new DateBean("Today",      1) );
		al.add( new DateBean("Last week",  2) );
		al.add( new DateBean("Last month", 3) );
		al.add( new DateBean("Last year",  4) );
		
		context.setAttribute(Constants_Scope.ALL_DATES_KEY, al);
		if(IS_DEBUG) log.debug("Application scope initialized:\n\n" +
								Constants_Scope.MANCHESTERSVC_KEY        + ":\tmanchesterService Java class\n" + 
								Constants_Scope.LABELS_KEY               + ":\tResourceBundle by Struts\n" + 
								Constants_Scope.SSOENABLED_KEY           + ":\t(" + context.getAttribute(Constants_Scope.SSOENABLED_KEY) + ")  true/false boolean: is sso enabled?\n" + 
								Constants_Scope.ALL_TASKTYPES_KEY        + ":\t\tall task types\n" + 
								Constants_Scope.ALL_TASKPRIORITIES_KEY   + ":\tall task priorities\n" + 
								Constants_Scope.ALL_TASKDIFFICULTIES_KEY + ":\tall task difficulties\n" + 
								Constants_Scope.ALL_TASKSTATUSES_KEY     + ":\tall task statuses\n" +
								Constants_Scope.ALL_LOCALES_KEY          + ":\t\tall locales\n" +
								Constants_Scope.ALL_USERTYPES_KEY        + ":\t\tall user types\n" +
								Constants_Scope.ALL_ACCESSES_KEY         + ":\t\tall access types for projects (read/write)\n" +
								Constants_Scope.ALL_PROJECTS_KEY         + ":\t\tall projects\n" +
								Constants_Scope.ALL_USERS_KEY            + ":\t\tall users\n" +
								Constants_Scope.ALL_DATES_KEY            + ":\t\tArrayList: options for the When filter\n");
	  } catch(Exception e) {
		log.error("Error while initializing application scope: "+e.toString());
		e.printStackTrace();
	  }
	}
	
	
	
	
	/**
	 * This is where the application starts at app server startup time:
	 * 1) Cache EJB homes
	 * 2) Initialize application scope
	 * @return void
	 */
	public void init() throws ServletException {
	  try {
		cacheHomes();
		initApplicationScope();
	  } catch(Exception e) {
	  	log.error("Error in StartupServlet.init(): "+e.toString());
	  	e.printStackTrace();	
	  }
	}	
}
