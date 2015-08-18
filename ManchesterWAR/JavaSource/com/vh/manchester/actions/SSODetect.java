/**
 * @file SSODetect
 * @author peter.szocs
 * @version 1.0
 * 
 * This is the very first action of the hub.
 * It checks whether SSO is enabled and either:
 *   (a) logs the user in thru Login.do, or
 *   (b) forwards to login.jsp
 */


package com.vh.manchester.actions;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.vh.manchester.util.ActionUtils;
import com.vh.manchester.util.Constants_Scope;
import com.vh.manchester.util.SessionUtils;

/**
 * @version 	1.0
 * @author		peter.szocs
 */
public class SSODetect extends Action {
	
	private static Logger log = Logger.getLogger(SSODetect.class);
	private static final boolean IS_DEBUG = log.isDebugEnabled();

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//if(IS_DEBUG) log.debug("inside");
		String forward = "failure";
		boolean ssoEnabled = false;
		
		if(request.getUserPrincipal()!=null) {
			String userName = request.getRemoteUser();
			if(IS_DEBUG) log.debug("SSO enabled, userName="+userName);
			if((userName!=null) && (!"".equals(userName))) {
				ssoEnabled = true;
				forward = "success";
			}			
		} else if(IS_DEBUG) log.debug("SSO disabled, forwarding to login.jsp");
		ServletContext context = this.getServlet().getServletContext();
		if(context.getAttribute(Constants_Scope.SSOENABLED_KEY)==null) {
			if(IS_DEBUG) log.debug("Initializing application scope variable "+Constants_Scope.SSOENABLED_KEY+"="+ssoEnabled);
			context.setAttribute(Constants_Scope.SSOENABLED_KEY, new Boolean(ssoEnabled));
		} else if(IS_DEBUG) log.debug("SSOENABLED var already in application scope: "+Constants_Scope.SSOENABLED_KEY+"="+ssoEnabled);
		
		return mapping.findForward(forward);
	}
}