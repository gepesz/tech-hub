/**
 * @file MissingResourcesException
 * @author peter.szocs
 * 
 * Thrown when a reference to a non-existing resource bundle/locale
 * combination is made.
 */


package com.vh.manchester.service.exception;


/**
 * The VH Corporation
 *
 * Copyright (c) 2003 The VH Corporation.  All rights reserved.  
 * Copying or reproduction without prior written approval is prohibited.
 * 
 * @author  peter.szocs
 * @version 1.0
 */
public class MissingResourcesException extends Exception {

    public MissingResourcesException() {
        super("");
    }

    public MissingResourcesException(String msg) {
        super(msg);
    }

    public MissingResourcesException(String msg, Throwable cause) {
        super(msg + " - " + cause);
    }

    public MissingResourcesException(Throwable cause) {
        super(cause.toString());
    }
}
