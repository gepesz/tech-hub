/**
 * @file EJBHomeCache
 * @author peter.szocs
 * 
 * This file uses a java.util.HashMap to store EJB InitialContext lookups to
 * speed up later EJB lookups.  Primary use is to return an EJB Home interface
 * fast from the HashMap.
 */


package com.vh.manchester.util;

import java.util.HashMap;

import javax.ejb.EJBLocalHome;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

/**
 * The VH Corporation
 *
 * Copyright (c) 2003 The VH Corporation.  All rights
 * reserved.  Copying or reproduction without prior written 
 * approval is prohibited.
 * 
 * @author  peter.szocs
 * @version 1.0
 */
public class EJBHomeCache {

  private static HashMap homes=new HashMap();
  private static InitialContext mContext;
  private static Logger log = Logger.getLogger(EJBHomeCache.class);

  private static synchronized void establishInitialContext() {
    try {
      if(mContext==null) {
        mContext=new InitialContext();
        if(log.isDebugEnabled()) log.debug("New initial context created");
      }
    } catch (Exception e) {
      log.error("Exception in EJBHomeCache.establishInitialContext(): "+e.toString());
      e.printStackTrace();
    }
  }

  public static synchronized InitialContext getInitialContext(){
    establishInitialContext();
    return mContext;
  }


  public static synchronized EJBLocalHome lookup(String homeType) throws NamingException {

    establishInitialContext();
    EJBLocalHome home=(EJBLocalHome)homes.get(homeType);
    if(home==null) {
      if(mContext!=null) {
        try {
        	home=(EJBLocalHome) mContext.lookup(homeType);
        } catch (Exception e) {
          log.error("Exception: Unable to lookup home for " + homeType + " in EJBHomeCache.getHome(): "+e.toString());
		  e.printStackTrace();
        }

        if(home!=null) {
          homes.put(homeType,home);
          if(log.isDebugEnabled()) log.debug("Cached home for "+homeType);
        } else {
          log.error("Unable to lookup home for " + homeType + " in EJBHomeCache.getHome() - 222");
        }
      }
    } else {
      if(log.isDebugEnabled()) log.debug("Successful lookup of home for " + homeType);
    }
    return home;
  }
}





