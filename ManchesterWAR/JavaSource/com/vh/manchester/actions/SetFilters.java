/**
 * @file SetFilters
 * @author peter.szocs
 * @version 1.0
 * 
 * Changes the filterform stored in the session by the new filters.
 * Called when any of the filters have changed.
 */


package com.vh.manchester.actions;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.vh.manchester.service.ManchesterService;
import com.vh.manchester.util.ActionUtils;
import com.vh.manchester.util.Constants;
import com.vh.manchester.util.SessionUtils;

/**
 * @version 	2.0
 * @author		peter.szocs
 */
public class SetFilters extends BaseAction {

	private static Logger log = Logger.getLogger(SetFilters.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ManchesterService man) throws Exception {
		//if(IS_DEBUG) log.debug("inside");

		DynaValidatorForm filterform = (DynaValidatorForm) form;
		String type = request.getParameter("t");
		
		if(type!=null) {
			if("0".equals(type)) { // My
				filterform.set("fstatus",     Constants.TASKSTATUS_OPEN);
				filterform.set("fassignedTo", SessionUtils.getUserId(request));
			} else if("1".equals(type)) { // All
				filterform.set("fstatus", Constants.TASKSTATUS_OPEN);
			} else if("2".equals(type)) { // Verify
				filterform.set("fstatus",     Constants.TASKSTATUS_RSLVD);
				filterform.set("freportedBy", SessionUtils.getUserId(request));
			}
		}
		SessionUtils.setFiltersIntoSession(request, filterform);
								
		return mapping.findForward("success");
	}
}