/**
 * @file PopulateHeader
 * @author peter.szocs
 * @version 1.0
 * 
 * Prepopulate vars for header.jsp.
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
import com.vh.manchester.util.SessionUtils;

/**
 * @version 	1.0
 * @author		peter.szocs
 */
public class PopulateHeader extends BaseAction {

	private static Logger log = Logger.getLogger(PopulateHeader.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ManchesterService man) throws Exception {
		//if(IS_DEBUG) log.debug("inside");
		
		// prepopulating form
		DynaValidatorForm projectform = (DynaValidatorForm) form;
		projectform.set("project", SessionUtils.getProjectId(request));
				
		return mapping.findForward("success");
	}
}