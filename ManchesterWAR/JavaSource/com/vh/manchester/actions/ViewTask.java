/**
 * @file ViewTask
 * @author peter.szocs
 * @version 1.0
 * 
 * View action for editing tasks.
 */


package com.vh.manchester.actions;

import java.util.ArrayList;
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
import com.vh.manchester.util.RequestUtils;
import com.vh.manchester.util.SessionUtils;

/**
 * @version 	1.0
 * @author		peter.szocs
 */
public class ViewTask extends BaseAction {

	private static Logger log = Logger.getLogger(ViewTask.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ManchesterService man) throws Exception {
		//if(IS_DEBUG) log.debug("inside");

		// prepopulating form
		Integer id = new Integer(request.getParameter("id"));
		Task task = man.findTaskById(id);

		DynaValidatorForm taskform = (DynaValidatorForm) form;
		taskform.set("id", 		    id);
		taskform.set("title", 		task.getTitle());
		taskform.set("summary", 	task.getSummary());
		taskform.set("type", 		task.getTaskType().getId());
		taskform.set("status", 		task.getTaskStatus().getId());
		taskform.set("priority", 	task.getPriority().getId());
		taskform.set("difficulty", 	task.getDifficulty().getId());
		taskform.set("plannedDate", task.getPlannedDate().toString());
		taskform.set("assignedTo", 	task.getWho().getId());
		taskform.set("comment", 	task.getComment());
		taskform.set("progress", 	task.getProgress());
		RequestUtils.setTaskForm(request, taskform);
		RequestUtils.setTask(request, task);
		
		return mapping.findForward("success");
	}
}