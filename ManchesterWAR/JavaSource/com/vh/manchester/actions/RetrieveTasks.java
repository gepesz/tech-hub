/**
 * @file RetrieveTasks
 * @author peter.szocs
 * @version 1.0
 * 
 * This is the most used action of the hub.
 * It retrieves the requested tasks as set by the filters.
 */


package com.vh.manchester.actions;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.vh.manchester.bean.DateBean;
import com.vh.manchester.service.ManchesterService;
import com.vh.manchester.util.ActionUtils;
import com.vh.manchester.util.Constants;
import com.vh.manchester.util.RequestUtils;
import com.vh.manchester.util.SessionUtils;

/**
 * @version 	2.0
 * @author		peter.szocs
 */
public class RetrieveTasks extends BaseAction {

	private static Logger log = Logger.getLogger(RetrieveTasks.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ManchesterService man) throws Exception {
		//if(IS_DEBUG) log.debug("inside");

		DynaValidatorForm filterform = SessionUtils.getFiltersFromSession(request);
		Integer status      = (Integer) filterform.get("fstatus");
		Integer assignedTo  = (Integer) filterform.get("fassignedTo");
		Integer reportedBy  = (Integer) filterform.get("freportedBy");
		Integer when        = (Integer) filterform.get("fwhen");
		String searchString = (String)  filterform.get("fsearch");
		Date afterDate      = null;
		Date beforeDate     = null;
		if((when!=null)  && (when.intValue()!=-1)) {
			int i = when.intValue();
			Calendar c = Calendar.getInstance();
			beforeDate = new Date(c.getTime().getTime());
			switch(i) {
				case 1: //today
				        break;
				case 2: //this week
				        c.add(Calendar.WEEK_OF_MONTH, -1);
				        break;
				case 3: //this month
						c.add(Calendar.MONTH, -1);
						break;
				case 4: //this year
						c.add(Calendar.YEAR, -1);
						break;
			   default: //default
			   			break;
			}
			afterDate = new Date(c.getTime().getTime());
		} 
		/*
		if(IS_DEBUG) {
		  log.debug("status="+status);
		  log.debug("when="+when);
		  log.debug("afterDate="+afterDate);
		  log.debug("beforeDate="+beforeDate);
		  log.debug("assignedTo="+assignedTo);
		  log.debug("reportedBy="+reportedBy);
		  log.debug("searchString="+searchString);
		}
		*/
		Collection taskColl = man.findTasks(SessionUtils.getProjectId(request), status, afterDate, beforeDate, assignedTo, reportedBy, searchString);
		RequestUtils.setTaskColl(request, taskColl);
		//request.setAttribute("taskColl", taskColl);
		//request.setAttribute("filterform", filterform);
						
		return mapping.findForward("success");
	}
}