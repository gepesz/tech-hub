/**
 * @file Monitor
 * @author peter.szocs
 * @version 1.0
 * 
 * Monitor: view action for monitoring user activity in the hub 
 * such as last login, nof open issues etc.
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

import com.vh.manchester.bean.MonitorBean;
import com.vh.manchester.ejb.Task;
import com.vh.manchester.ejb.User;
import com.vh.manchester.service.ManchesterService;
import com.vh.manchester.util.Constants;
import com.vh.manchester.util.RequestUtils;

/**
 * @version 	1.0
 * @author		peter.szocs
 */
public class Monitor extends BaseAction {

	private static Logger log = Logger.getLogger(Monitor.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ManchesterService man) throws Exception {
		//if(IS_DEBUG) log.debug("inside");
		
		ArrayList arr = new ArrayList();

		Iterator it = man.findAllUser().iterator();
		while(it.hasNext()) {
			User user = (User) it.next();
			MonitorBean mbean = new MonitorBean();
			mbean.setUserId(user.getId());
			mbean.setUserName(user.getUsername());
			mbean.setEmail(user.getEmail());
			mbean.setLastLogin(user.getLastLogin());
			mbean.setNofLogins(user.getNofLogins());
			
			double openProgress = 0;
			int nofOpen  = 0;
			int nofRslvd = 0;
			int nofOther = 0;
			int nofTotal = 0;
			
			Iterator assignedTasks = man.findTaskByAssignedTo(user.getId()).iterator();
			while(assignedTasks.hasNext()) {
				Task task = (Task) assignedTasks.next();
				Integer taskStatus = task.getTaskStatus().getId();
				if(taskStatus.equals(Constants.TASKSTATUS_OPEN)) {
					nofOpen++;
					if(task.getProgress()!=null) openProgress+=task.getProgress().intValue();
				} else if(taskStatus.equals(Constants.TASKSTATUS_RSLVD)) {
					nofRslvd++;
				} else {
					nofOther++;
				}
				nofTotal++;
			}
			if(nofOpen>0) mbean.setAvgOpenProgress( java.lang.Math.rint(openProgress/nofOpen) );
			mbean.setNofOpen(nofOpen);
			mbean.setNofRslvd(nofRslvd);
			mbean.setNofOther(nofOther);
			mbean.setNofTotal(nofTotal);
			
			arr.add(mbean);
		}
		RequestUtils.setMonitorColl(request, arr);

		return mapping.findForward("success");
	}
}