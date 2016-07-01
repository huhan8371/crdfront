package org.demo.exception;

/**
 * Created by hanhu on 16/6/20.
 */


/**
 * 业务异常
 */
public class CrdException extends RuntimeException
{
    public CrdException (String message) { super(message);}

    public CrdException(String message, Throwable cause) {
        super(message, cause);
    }
}
