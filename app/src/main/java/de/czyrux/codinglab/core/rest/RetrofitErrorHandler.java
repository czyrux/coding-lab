package de.czyrux.codinglab.core.rest;

import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;

import de.czyrux.codinglab.core.exception.ConnectionTimeoutException;
import de.czyrux.codinglab.core.exception.NoNetworkException;
import de.czyrux.codinglab.core.exception.UnAuthorizedException;
import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Retrofit error handler implementation to handle network or unauthorized errors.
 */
public class RetrofitErrorHandler implements ErrorHandler {

    @Override
    public Throwable handleError(RetrofitError cause) {
        RetrofitError.Kind kind = cause.getKind();

        if (kind == RetrofitError.Kind.NETWORK) {
            if (cause.getCause() instanceof SocketTimeoutException) {
                return new ConnectionTimeoutException("Connection timeout error.");
            } else {
                return new NoNetworkException("No network available");
            }
        } else if (kind == RetrofitError.Kind.HTTP) {
            Response httpResponse = cause.getResponse();
            if (httpResponse != null && httpResponse.getStatus() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                return new UnAuthorizedException("Unauthorized permission");
            }
        }

        return cause.getCause();
    }
}
