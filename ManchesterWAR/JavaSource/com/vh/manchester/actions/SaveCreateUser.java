/**
 * @file SaveCreateUser
 * @author peter.szocs
 * @version 1.0
 * 
 * Save action for creating users.
 */


package com.vh.manchester.actions;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.vh.manchester.util.SessionUtils;

/**
 * @version 	1.0
 * @author		peter.szocs
 */
public class SaveCreateUser extends BaseAction {

	private static Logger log = Logger.getLogger(SaveCreateUser.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ManchesterService man) throws Exception {
		//if(IS_DEBUG) log.debug("inside");

		if(!isCancelled(request)) {
			DynaValidatorForm userform = (DynaValidatorForm) form;
			String username = (String)  userform.get("username");
			String password = (String)  userform.get("password");
			String email    = (String)  userform.get("email");
			Integer type    = (Integer) userform.get("type");
			Integer locale  = (Integer) userform.get("locale");

			// validate for already existing username
			if(man.checkUsername(username, null)) {
				if(Constants.HASH_PASSWORD) man.createUser(username, DigestUtils.md5Hex(password), email, null, 0, locale, type, new Integer(1));
				else man.createUser(username, password, email, null, 0, locale, type, new Integer(1));
				//update session constants
				ServletContext context = this.getServlet().getServletContext();
				context.setAttribute(Constants_Scope.ALL_USERS_KEY, man.findAllUser());
				SessionUtils.setDevelopersForProjectIntoSession(request, man.findDevelopersByProject(SessionUtils.getProjectId(request)));
			} else {
				log.warn("Create new user failed because of existing username: "+username);
				ActionErrors errors = new ActionErrors();
				errors.add("errors.username", new ActionError("errors.username"));
				saveErrors(request, errors);
				return mapping.findForward("failure");						
			}
		}
		
		return mapping.findForward("success");
	}
}