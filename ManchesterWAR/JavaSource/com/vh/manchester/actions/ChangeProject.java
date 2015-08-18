/**
 * @file ChangeProject
 * @author peter.szocs
 * @version 1.0
 * 
 * Used to switch projects.
 */


package com.vh.manchester.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.vh.manchester.service.ManchesterService;
import com.vh.manchester.util.ActionUtils;
import com.vh.manchester.util.RequestUtils;
import com.vh.manchester.util.SessionUtils;

/**
 * @version 	1.0
 * @author		peter.szocs
 */
public class ChangeProject extends BaseAction {

	private static Logger log = Logger.getLogger(ChangeProject.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ManchesterService man) throws Exception {
		//if(IS_DEBUG) log.debug("inside");
		
		DynaValidatorForm projectform = (DynaValidatorForm) form;
		Integer projectId  = (Integer) projectform.get("project");
		Integer userId = SessionUtils.getUserId(request);
		Integer accessId = man.getProjectAccess(projectId, userId);
		//if(IS_DEBUG) log.debug("Project, user, access:  "+projectId+", "+userId+", "+accessId);
		
		if(accessId!=null) { // user has read XOR write access to project
			man.updateUserLastProject(userId, projectId);
			SessionUtils.setProjectIntoSession(request, man.findProjectById(projectId));
			SessionUtils.setProjectAccessIdIntoSession(request, accessId);
		} else { // user has NO access to project
			if(IS_DEBUG) log.debug("User '"+SessionUtils.getUserFromSession(request).getUsername()+"' has no access to project "+projectId);
			RequestUtils.setNoProjectAccess(request);
		}
		
		return mapping.findForward("success");
	}
}