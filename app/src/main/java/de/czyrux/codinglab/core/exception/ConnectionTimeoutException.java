package de.czyrux.codinglab.core.exception;

public class ConnectionTimeoutException extends Throwable {

    public ConnectionTimeoutException(String detailMessage) {
        super(detailMessage);
    }
}
