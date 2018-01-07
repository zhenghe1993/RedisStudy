package com.jmper.exception;

/**
 * @author 郑和明
 * @version 1.0 (createTime:2018-01-07 21:44:53)
 */
public class LoginException extends Exception{


    public LoginException() {
        super();
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
