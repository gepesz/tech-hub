/**
 * @file Sequence
 * @author peter.szocs
 * 
 * Generates a sequence for creating ejbs.
 */


package com.vh.manchester.notused;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import javax.ejb.FinderException;
import javax.ejb.CreateException;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import com.vh.manchester.service.exception.ServiceException;
import com.vh.manchester.util.Constants;
import com.vh.manchester.util.EJBHomeCache;

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
public class Sequence {

    private static Logger log=Logger.getLogger(Sequence.class);
  
   /**
    * This is a utility method that creates a connection and prepared statement
    * @param: pQry the query string
    */
    private static PreparedStatement getStatement(Connection pCon, String pQry) throws ServiceException {
      try {
        return pCon.prepareStatement(pQry);
      } catch (SQLException e) {
      	log.error("Error creating preparedStatement: "+e.toString());
        throw new ServiceException("Error creating preparedStatement: " + e.getMessage());
      }
    }


    /**
     * This is a utility method that opens a connection using DataSource from the pool
     */
    private static Connection openConnection() throws ServiceException {
	  try {
   		InitialContext mContext = EJBHomeCache.getInitialContext();
    	javax.sql.DataSource mDS = (javax.sql.DataSource)mContext.lookup(Constants.theDataSource);
    	Connection conn = mDS.getConnection();
		return conn;
	  } catch (Exception e) {
	  	log.error("Database connection could not be established: "+e.toString());
	  	throw new ServiceException("Error on openConnection(): " + e.getMessage());
	  }
    }

    /**
     * This is a utility method that disposes of a connection and prepared statement
     */
    private static void closeConnection(Connection pCon) throws ServiceException {
	  try {
		pCon.close();
	  } catch (SQLException e) {
	  	log.error("Error during closeConnection()", e);
		throw new ServiceException("Error on closeConnection(): " + e);
	  }
    }

    /**
     * Returns the next value of the sequence
     * @param name the sequence name
     * @return next value of the sequence
     * @throws ServiceException on error
     */
    public static synchronized Integer getId(String name) throws ServiceException{

      Connection mCon = openConnection();
      
      // Oracle: 
      //String mSequenceQry = "SELECT " + name + "_SEQ.nextval AS SEQVALUE FROM DUAL";
	  
	  // PostgreSQL:
	  String mSequenceQry = "SELECT nextval('" + name + "_seq') AS SEQVALUE";
	  
	  PreparedStatement mPreparedStatement = getStatement(mCon, mSequenceQry);
	  Integer mId = null;

	  // Get id value from sequence
	  try {
  	    ResultSet rs = mPreparedStatement.executeQuery();
	    while (rs.next()) {
		  mId = new Integer(rs.getInt("SEQVALUE"));
	    }
	  } catch (Exception e) {
	    log.error("Error while executing "+ name +"_SEQ sequence query: "+e.toString());
        throw new ServiceException("Error while executing "+ name + "_SEQ query: " + e.getMessage());
	  } finally {
	    closeConnection(mCon);
	  }
      return mId;
    }
}

