package org.demo.exception;

/**
 * Created by hanhu on 16/6/20.
 */
public class UpdateOptException extends CrdException
{
    public UpdateOptException(String message) {
        super(message);
    }

    public UpdateOptException(String message, Throwable cause) {
        super(message, cause);
    }
}
