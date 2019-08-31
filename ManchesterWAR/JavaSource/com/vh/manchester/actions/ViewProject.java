/**
 * @file ViewProject
 * @author peter.szocs
 * @version 1.0
 * 
 * View action for editing projects.
 */


package com.vh.manchester.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.vh.manchester.bean.AccessBean;
import com.vh.manchester.ejb.Project;
import com.vh.manchester.ejb.User;
import com.vh.manchester.service.ManchesterService;
import com.vh.manchester.util.Constants_Scope;
import com.vh.manchester.util.RequestUtils;

/**
 * @version 	1.0
 * @author		peter.szocs
 */
public class ViewProject extends BaseAction {

	private static Logger log = Logger.getLogger(ViewProject.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ManchesterService man) throws Exception {
		//if(IS_DEBUG) log.debug("inside");

		// get project id from request
		Integer id;
		String projectId = request.getParameter("id");
		if(projectId!=null) id = new Integer(projectId); 
		else id = (Integer)request.getAttribute("id");
		
		Project p = man.findProjectById(id);

		// iterate thru all users, populate accessbean with info
		ArrayList<AccessBean> arr = new ArrayList<AccessBean>();    // holds the project access beans		
		Iterator it = ((Collection)request.getSession().getServletContext().getAttribute(Constants_Scope.ALL_USERS_KEY)).iterator();
		while(it.hasNext()) {
			User user = (User) it.next();
			AccessBean abean = new AccessBean();
			abean.setUserId(user.getId());
			abean.setUserName(user.getUsername());
			abean.setEmail(user.getEmail());
				
			Integer accessId = man.getProjectAccess(id, abean.getUserId()); 				
			if(accessId!=null) {
				abean.setHasAccess(true);
				abean.setAccessId(accessId);
			}

			arr.add(abean);
		}

		// prepopulate form, set request vars
		DynaValidatorForm projectform = (DynaValidatorForm) form;
		projectform.set("project", id);
		projectform.set("desc",    p.getDesc());
		RequestUtils.setProject(request, p);
		RequestUtils.setProjectAccessColl(request, arr);

		return mapping.findForward("success");
	}
}