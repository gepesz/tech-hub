/**
 * @file DeleteProjects
 * @author peter.szocs
 * @version 1.0
 * 
 * Action for deleting projects.
 */


package com.vh.manchester.actions;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.vh.manchester.service.ManchesterService;
import com.vh.manchester.util.Constants_Scope;
import com.vh.manchester.util.RequestUtils;
import com.vh.manchester.util.SessionUtils;

/**
 * @version 	1.0
 * @author		peter.szocs
 */
public class DeleteProjects extends BaseAction {

	private static Logger log = Logger.getLogger(DeleteProjects.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ManchesterService man) throws Exception {
		//if(IS_DEBUG) log.debug("inside");

		//delete the selected users
		DynaValidatorForm deleteform = (DynaValidatorForm) form;
		Integer[] projIds = (Integer[]) deleteform.get("id"); // holds selected projIds only (ie project ids to be deleted)
		String userName = SessionUtils.getUserFromSession(request).getUsername(); // used for logging only: to log who is deleting the projects
		man.deleteProjects(projIds, userName);
		
		//update session constants
		ServletContext context = this.getServlet().getServletContext();
		context.setAttribute(Constants_Scope.ALL_PROJECTS_KEY, man.findAllProject());
		RequestUtils.setProjectsChanged(request, 3);
		
		return mapping.findForward("success");
	}
}