package de.czyrux.codinglab.core.exception;

public class UnAuthorizedException extends Throwable {
    public UnAuthorizedException(String detailMessage) {
        super(detailMessage);
    }
}
