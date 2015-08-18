/**
 * @file ServiceException
 * @author peter.szocs
 * 
 * Base exception that every service exception must extend.
 */


package com.vh.manchester.service.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * The VH Corporation
 *
 * Copyright (c) 2003 The VH Corporation.  All rights reserved.  
 * Copying or reproduction without prior written approval is prohibited.
 * 
 * @author  peter.szocs
 * @version 1.0
 */
public class ServiceException extends Exception {

    public ServiceException() {
        super("");
    }

    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(String msg, Throwable cause) {
        super(msg + " - " + cause);
    }

    public ServiceException(Throwable cause) {
        super(cause.toString());
    }
}
