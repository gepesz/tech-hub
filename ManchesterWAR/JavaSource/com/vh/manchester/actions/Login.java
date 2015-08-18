/**
 * @file Login
 * @author peter.szocs
 * @version 1.0
 * 
 * Standard login action: authenticate user, setup session vars.
 */


package com.vh.manchester.actions;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.vh.manchester.ejb.Project;
import com.vh.manchester.ejb.User;
import com.vh.manchester.service.ManchesterService;
import com.vh.manchester.service.ManchesterServiceImpl_Postgre;
import com.vh.manchester.util.ActionUtils;
import com.vh.manchester.util.Constants;
import com.vh.manchester.util.Constants_Scope;
import com.vh.manchester.util.MessageUtils;
import com.vh.manchester.util.SessionUtils;

/**
 * @version 	1.0
 * @author		peter.szocs
 */
public class Login extends Action {
	
	private static Logger log = Logger.getLogger(Login.class);
	private static final boolean IS_DEBUG = log.isDebugEnabled();

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//if(IS_DEBUG) log.debug("inside");

		ActionErrors errors = new ActionErrors();
		User theUser = null;
		ManchesterService man = (ManchesterService) SessionUtils.getService(Constants_Scope.MANCHESTERSVC_KEY, request);
		boolean ssoEnabled = ((Boolean) request.getSession().getServletContext().getAttribute(Constants_Scope.SSOENABLED_KEY)).booleanValue();
		if(IS_DEBUG) log.debug("SSO enabled="+ssoEnabled);
		
		//initialize variable theUser based on ssoEnabled or not
		if(ssoEnabled) {
			
			//get username from request
			String username = request.getRemoteUser();

			//find the user
			theUser = man.findUserByUsername(username);
						
		} else {
			
			//get username & password from request
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			//validate
			if((username==null) || ("".equals(username))) {
			  errors.add("errors.required", new ActionError("errors.required", MessageUtils.getMessage(Constants_Scope.LABELS_KEY, "com.vh.label.Username", request)));      
			}
			if((password==null) || ("".equals(password))) {
			  errors.add("errors.required", new ActionError("errors.required", MessageUtils.getMessage(Constants_Scope.LABELS_KEY, "com.vh.label.Password", request)));      
			}    
			if(errors.size()>0) {
			  saveErrors(request, errors);
			  return mapping.findForward("failure");
			}
			
			//authenticate
			if(Constants.HASH_PASSWORD) theUser = man.authenticate(username, DigestUtils.md5Hex(password));
			else theUser = man.authenticate(username, password);

		}	

		// successful authentication
		if(theUser!=null) {
			man.updateLoginInfo(theUser);
			Config.set(request.getSession(), Config.FMT_LOCALE, man.getUserLocale(theUser));  //use this to set the locale into session for <fmt:message key=... /> entries
			//this.setLocale(request, man.getUserLocale(theUser));                            //use this to set the locale into session for <b:message key=... /> entries
			Project theProject = theUser.getLastProject();
			SessionUtils.setUserIntoSession(request, theUser);
			SessionUtils.setProjectIntoSession(request, theProject);
			SessionUtils.setProjectAccessIdIntoSession(request, man.getProjectAccess(theProject.getId(), theUser.getId()));
			SessionUtils.setDevelopersForProjectIntoSession(request, man.findDevelopersByProject(theProject.getId()));

		} else {
			errors.add("errors.password", new ActionError("errors.password"));
			saveErrors(request, errors);
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}
}