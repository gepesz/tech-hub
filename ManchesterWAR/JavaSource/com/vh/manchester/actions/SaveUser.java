/**
 * @file SaveUser
 * @author peter.szocs
 * @version 1.0
 * 
 * Save action for editing users.
 */


package com.vh.manchester.actions;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.vh.manchester.service.ManchesterService;
import com.vh.manchester.util.ActionUtils;
import com.vh.manchester.util.Constants;
import com.vh.manchester.util.Constants_Scope;
import com.vh.manchester.util.RequestUtils;
import com.vh.manchester.util.SessionUtils;

/**
 * @version 	1.0
 * @author		peter.szocs
 */
public class SaveUser extends BaseAction {

	private static Logger log = Logger.getLogger(SaveUser.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ManchesterService man) throws Exception {
		//if(IS_DEBUG) log.debug("inside");

		DynaValidatorForm userform = (DynaValidatorForm) form;
		Integer myInt  = (Integer) userform.get("my");
		int my; // my is used to figure out whether to go to RetrieveTasks (coming from Settings link) XOR go to RetrieveUsers (coming from Admin Console)
		if((myInt!=null) && (myInt.intValue()==1)) my=1;
		else my=0;

		if(!isCancelled(request)) {
			Integer id 			= (Integer) userform.get("id");
			Integer pwChanged	= (Integer) userform.get("pwChanged");
			String username 	= (String)  userform.get("username");
			String password 	= (String)  userform.get("password");
			String email    	= (String)  userform.get("email");
			Integer type    	= (Integer) userform.get("type");
			Integer locale  	= (Integer) userform.get("locale");

			// validate for already existing username
			if(man.checkUsername(username, id)) {
				// update the user
				if(Constants.HASH_PASSWORD && (pwChanged.intValue()==1)) man.updateUser(id, username, DigestUtils.md5Hex(password), email, type, locale);
				else man.updateUser(id, username, password, email, type, locale);
				//update session constants
				ServletContext context = this.getServlet().getServletContext();
				context.setAttribute(Constants_Scope.ALL_USERS_KEY, man.findAllUser());
				SessionUtils.setDevelopersForProjectIntoSession(request, man.findDevelopersByProject(SessionUtils.getProjectId(request)));
			} else {
				log.warn("Update user failed because of existing username: "+username);
				ActionErrors errors = new ActionErrors();
				errors.add("errors.username", new ActionError("errors.username"));
				saveErrors(request, errors);
				return mapping.findForward("failure");						
			}


			// if coming from Settings link AND locale changed, set a request attribute so that the entire page will be reloaded in the new language
			if(my==1) {
				Locale currLocale = (Locale) Config.get(request.getSession(), Config.FMT_LOCALE);  //use this to get the current locale IFF <fmt:message key=... /> tags are used
				//Locale currLocale = this.getLocale(request);                                     //use this to get the current locale IFF <b:message key=... /> tags are used
				Locale  newLocale = ActionUtils.convertLocale( man.findLocaleById(locale) );
				if(!currLocale.equals(newLocale)) {
					Config.set(request.getSession(), Config.FMT_LOCALE, newLocale);  //use this to set the locale into session for <fmt:message key=... /> entries
					//this.setLocale(request, newLocale);                            //use this to set the locale into session for <b:message key=... /> entries
					RequestUtils.setLocaleChanged(request);
				}
			}
		}

		if(my==1) return mapping.findForward("viewtasks");
		else return mapping.findForward("viewusers");
	}
}