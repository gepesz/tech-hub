/**
 * @file BaseAction
 * @author peter.szocs
 * @version 1.0
 * 
 * BaseAction that extends Struts Action that every Action
 * should extend and implement the abstract execute() method.
 */


package com.vh.manchester.actions;

import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.vh.manchester.service.ManchesterService;
import com.vh.manchester.service.Service;
import com.vh.manchester.service.exception.ServiceException;
import com.vh.manchester.util.ActionUtils;
import com.vh.manchester.util.Constants;
import com.vh.manchester.util.Constants_Scope;
import com.vh.manchester.util.SessionUtils;

/**
 * The VH Corporation
 *
 * Copyright (c) 2003 The VH Corporation.  All rights reserved.  
 * Copying or reproduction without prior written  approval is prohibited.
 * 
 */
public abstract class BaseAction extends Action {

	private static Logger log = Logger.getLogger(BaseAction.class);
	protected static boolean IS_DEBUG = log.isDebugEnabled();	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;

		try {

			// Checking for expired session
			if(SessionUtils.sessionExpired(request)) {
				if(IS_DEBUG) log.debug("Session expired, forwarding to logout");
				forward = mapping.findForward("logout");	
			} else {
				ManchesterService man = (ManchesterService) SessionUtils.getService(Constants_Scope.MANCHESTERSVC_KEY, request);
      			forward = execute(mapping, form, request, response, man);
			}

		} catch (ServiceException se) {
			log.error("ServiceException: "+se.toString());
			se.printStackTrace();
			forward = SessionUtils.handleThrowable(request, se, mapping);

		} catch (Exception e) {
			log.error("Exception: "+e.toString());
			e.printStackTrace();
			forward = SessionUtils.handleThrowable(request, e, mapping);			
		}

		// Finish with
		return forward;
	}




	abstract public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		ManchesterService service)
		throws Exception;

}