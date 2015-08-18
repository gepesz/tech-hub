/**
 * @file SaveCreateTask
 * @author peter.szocs
 * @version 1.0
 * 
 * Save action for creating tasks.
 */


package com.vh.manchester.actions;

import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.vh.manchester.ejb.Task;
import com.vh.manchester.service.ManchesterService;
import com.vh.manchester.util.ActionUtils;
import com.vh.manchester.util.Constants;
import com.vh.manchester.util.Constants_Scope;
import com.vh.manchester.util.Mailer;
import com.vh.manchester.util.SessionUtils;

/**
 * @version 	1.0
 * @author		peter.szocs
 */
public class SaveCreateTask extends BaseAction {

	private static Logger log = Logger.getLogger(SaveCreateTask.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ManchesterService man) throws Exception {
		//if(IS_DEBUG) log.debug("inside");

		if(!isCancelled(request)) {
			//get data from form, create new task
			DynaValidatorForm taskform = (DynaValidatorForm) form;
			String title        = (String)  taskform.get("title");
			String summary      = (String)  taskform.get("summary");
			Integer type        = (Integer) taskform.get("type");
			Integer priority    = (Integer) taskform.get("priority");
			Integer difficulty  = (Integer) taskform.get("difficulty");	
			Integer status  	= (Integer) taskform.get("status");
			String plannedDate  = (String)  taskform.get("plannedDate");		
			Integer assignedTo  = (Integer) taskform.get("assignedTo");
			
			summary="("+SessionUtils.getUserFromSession(request).getUsername()+"):\n"+summary;
		
			Task task = man.createTask(title, summary, Date.valueOf(plannedDate), ActionUtils.today(), null, new Integer(0), difficulty, type, status, priority, assignedTo, SessionUtils.getUserId(request), SessionUtils.getProjectId(request));		
			if(Constants.EMAIL_ENABLED) Mailer.sendTaskEmail(task, request, null);

			//update session var
			SessionUtils.setTaskFormIntoSession(request, taskform);
		}
		
		return mapping.findForward("success");
	}
}