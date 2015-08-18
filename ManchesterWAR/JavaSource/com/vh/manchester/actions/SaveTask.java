/**
 * @file SaveTask
 * @author peter.szocs
 * @version 1.0
 * 
 * Save action for editing tasks.
 */


package com.vh.manchester.actions;

import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.vh.manchester.ejb.Task;
import com.vh.manchester.ejb.User;
import com.vh.manchester.service.ManchesterService;
import com.vh.manchester.util.ActionUtils;
import com.vh.manchester.util.Constants;
import com.vh.manchester.util.Constants_Scope;
import com.vh.manchester.util.Mailer;
import com.vh.manchester.util.MessageUtils;
import com.vh.manchester.util.SessionUtils;

/**
 * @version 	1.0
 * @author		peter.szocs
 */
public class SaveTask extends BaseAction {

	private static Logger log = Logger.getLogger(SaveTask.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ManchesterService man) throws Exception {
		//if(IS_DEBUG) log.debug("inside");

		ActionErrors errors = new ActionErrors();

		if(!isCancelled(request)) {
			DynaValidatorForm taskform = (DynaValidatorForm) form;
			Integer id         = (Integer) taskform.get("id");
			String title       = (String)  taskform.get("title");
			String summary     = (String)  taskform.get("summary");
			Integer type       = (Integer) taskform.get("type");
			Integer priority   = (Integer) taskform.get("priority");
			Integer difficulty = (Integer) taskform.get("difficulty");
			Integer status     = (Integer) taskform.get("status");
			Integer projectId  = (Integer) taskform.get("projectId");
			Integer assignedTo = (Integer) taskform.get("assignedTo");			
			String plannedDate = (String)  taskform.get("plannedDate");
			String comment     = (String)  taskform.get("comment");
			Integer progress   = (Integer) taskform.get("progress");

			// (1) set progress to 100 if resolved; (2) check if comment==empty
			if(!status.equals(Constants.TASKSTATUS_OPEN)) {
				if(comment.length()<=0) errors.add("errors.comment", new ActionError("errors.required", MessageUtils.getMessage(Constants_Scope.LABELS_KEY, "com.vh.label.Comment", request)));					
				else progress = new Integer(100);
			}
			//request.setAttribute("id", id);
			if(!errors.isEmpty()) {
				saveErrors(request, errors);
				return mapping.findForward("failure");						
			} else {
				comment=comment+"("+SessionUtils.getUserFromSession(request).getUsername()+")";			
				boolean updated=man.updateTask(id, title, summary, type, priority, difficulty, status, projectId, assignedTo, Date.valueOf(plannedDate), comment, progress);
				if((Constants.EMAIL_ENABLED) && updated) {
					Task task = man.findTaskById(id);
					Mailer.sendTaskEmail(task, request, SessionUtils.getUserFromSession(request));
				} 				
			} 				
		}
		
		return mapping.findForward("success");
	}
}