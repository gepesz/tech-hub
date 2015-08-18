/**
 * @file ViewCreateProject
 * @author peter.szocs
 * @version 1.0
 * 
 * View action for creating projects.
 */


package com.vh.manchester.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.vh.manchester.service.ManchesterService;

/**
 * @version 	1.0
 * @author		peter.szocs
 */
public class ViewCreateProject extends BaseAction {

	private static Logger log = Logger.getLogger(ViewCreateProject.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ManchesterService man) throws Exception {
		//if(IS_DEBUG) log.debug("inside");
			
		return mapping.findForward("success");
	}
}