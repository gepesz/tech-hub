/**
 * @file DeleteTasks
 * @author peter.szocs
 * @version 1.0
 * 
 * Action for deleting tasks.
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
import com.vh.manchester.util.SessionUtils;

/**
 * @version 	1.0
 * @author		peter.szocs
 */
public class DeleteTasks extends BaseAction {

	private static Logger log = Logger.getLogger(DeleteTasks.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ManchesterService man) throws Exception {
		//if(IS_DEBUG) log.debug("inside");

		DynaValidatorForm deleteform = (DynaValidatorForm) form;
		Integer[] taskIds = (Integer[]) deleteform.get("id"); // holds selected taskIds only (ie taskIds to be deleted)
		String userName = SessionUtils.getUserFromSession(request).getUsername(); // used for logging only: to log who is deleting the tasks
		man.deleteTasks(taskIds, userName);
		
		return mapping.findForward("success");
	}
}