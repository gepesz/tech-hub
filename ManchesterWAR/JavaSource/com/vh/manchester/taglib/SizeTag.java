/**
 * @file SizeTag
 * @author peter.szocs
 * @version 1.0
 * 
 * Print the size of the collection.
 */

package com.vh.manchester.taglib;

import java.util.Collection;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import org.apache.strutsel.taglib.utils.EvalHelper;

import com.vh.manchester.util.MessageUtils;

/**
 * The VH Corporation
 *
 * Copyright (c) 2005 The VH Corporation.  All rights reserved.  
 * Copying or reproduction without prior written approval is prohibited.
 * 
 * @author  Peter.Szocs
 * @version 1.0
 */
public class SizeTag extends TagSupport {
  private static Logger log=Logger.getLogger(SizeTag.class);	
  private String collName=null;
  private String scope=null;

  public void setCollection(String string) {
	collName = string;
  }

  public void setScope(String string) {
	scope = string;
  }

  public int doStartTag() throws JspException {
	JspWriter out = pageContext.getOut();
	try {
      EvalHelper.evalString("collection", collName, this, pageContext);
      if((collName!=null) && (!"".equals(collName))) {
        //init theColl (the actual collection)
        Collection theColl=null;
		int theScope=1;
		EvalHelper.evalString("scope", scope, this, pageContext);        
		if((scope!=null) && (!"".equals(scope))) {
          if("page".equalsIgnoreCase(scope)) theScope=0;
          else if("session".equalsIgnoreCase(scope)) theScope=2;
		  else if("application".equalsIgnoreCase(scope)) theScope=3;
		}
		switch(theScope) {
          case 0: theColl=(Collection) pageContext.getAttribute(collName); break;
          case 1: theColl=(Collection) pageContext.getRequest().getAttribute(collName); break;
          case 2: theColl=(Collection) pageContext.getSession().getAttribute(collName); break;
          case 3: theColl=(Collection) pageContext.getServletContext().getAttribute(collName); break;
          default:theColl=(Collection) pageContext.getRequest().getAttribute(collName); break;
		}
        //print size
        if(theColl!=null) out.print(theColl.size());
        else out.print("Collection '"+collName+"' cannot be found in '"+scope+"' scope");
      }
	} catch(Exception e) {
      log.error(e.getMessage());
      throw new JspException(e.toString());
	}
	return EVAL_PAGE;
  }
  
}