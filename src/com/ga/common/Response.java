package com.ga.common;

import com.ga.exception.ErrorCodes;

/**
 * The Response class will contain data which will be sent to the front end processor in terms of JSON object.
 * Controller will convert this object to the JASON.
 * 
 * @author Smit
 */
public class Response {

    private ErrorCodes message;
    private Object data;

    public ErrorCodes getMessage() {
        return message;
    }

    public void setMessage(ErrorCodes message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}