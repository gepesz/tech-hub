package com.vh.manchester.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;



/**
 * @file ConnectionFactory
 * @author peter.szocs
 * 
 * This file contains helper methods to establish and release connection,
 * preparedstatement and result set objects.
 */
public class ConnectionFactory {


  private static Logger log=Logger.getLogger(ConnectionFactory.class);
  private static DataSource mDataSource = null;



 /**
  * This is a utility method that returns the datasource
  */
  public static DataSource getDataSource() throws Exception {
    if(mDataSource==null) {
      try {
        InitialContext mContext = new InitialContext();
        mDataSource = (DataSource) mContext.lookup(Constants.theDataSource);      	
      } catch (Exception e) {
        log.error("Error while looking up DataSource: "+e.toString());
        throw e;
      }
    }
    return mDataSource;	
  }


 /**
  * This is a utility method that creates a connection
  */
  public static Connection getConnection() throws Exception {
    try {    	
      return getDataSource().getConnection();
    } catch (Exception e) {
      log.error("An error occured while creating Connection: "+e.toString());
      throw e;
    }
  }


 /**
  * This is a utility method that creates a prepared statement
  * @param pCon the connection
  * @param pQry the sql query
  */
  public static PreparedStatement getStatement(Connection pCon, String pQry) throws Exception {
    try {
      return pCon.prepareStatement(pQry);
    } catch (Exception e) {
      log.error("An error occured while creating PreparedStatement: "+e.toString());
      throw e;
    }
  }


 /**
  * This is a utility method that disposes of a result set
  */
  public static void close(ResultSet pRS) {
    try {
      pRS.close();
    } catch (Exception ignored) {}
  }


 /**
  * This is a utility method that disposes of a statement
  */
  public static void close(Statement pStmt) {
    try {
      pStmt.close();
    } catch (Exception ignored) {}
  }


 /**
  * This is a utility method that disposes of a connection
  */
  public static void close(Connection pCon) {
    try {
      pCon.close();
    } catch (Exception ignored) {}
  }

}
