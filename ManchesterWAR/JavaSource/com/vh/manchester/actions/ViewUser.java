/**
 * @file ViewUser
 * @author peter.szocs
 * @version 1.0
 * 
 * View action for editing users.
 */


package com.vh.manchester.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.vh.manchester.ejb.User;
import com.vh.manchester.service.ManchesterService;
import com.vh.manchester.util.RequestUtils;

/**
 * @version 	1.0
 * @author		peter.szocs
 */
public class ViewUser extends BaseAction {

	private static Logger log = Logger.getLogger(ViewUser.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ManchesterService man) throws Exception {
		//if(IS_DEBUG) log.debug("inside");

		// prepopulating form
		Integer id = new Integer(request.getParameter("id"));
		Integer myInt; //used to distinguish where to go (ie RetrieveTasks XOR RetrieveUsers) after the SaveUser action.  my=1 IFF coming from Settings link
		String my = request.getParameter("my");
		if(my==null) myInt = new Integer(0);
		else myInt = new Integer(my);
		User user = man.findUserById(id);

		DynaValidatorForm userform = (DynaValidatorForm) form;
		userform.set("id",          id);
		userform.set("my",          myInt);
		userform.set("username",    user.getUsername());
		userform.set("password",    user.getPassword());
		userform.set("email",  	    user.getEmail());
		userform.set("type",  	    user.getUserType().getId());
		userform.set("locale",      user.getLocale().getId());
		RequestUtils.setEditSettings(request, myInt);
		
		return mapping.findForward("success");
	}
}