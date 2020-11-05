package com.ares.core.common.exception;

/**
 * @description:
 * @author: yy 2020/05/09
 **/
public class UserException extends RuntimeException {

    private static final long serialVersionUID = 6109496485495487838L;
    private int code;
    private String message;

    public UserException() {
        super();
    }

    public UserException(String message){
        super(message);
        this.message = message;
    }

    public UserException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public UserException(String message, Throwable e) {
        super(message, e);
    }
}
