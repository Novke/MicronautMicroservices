package fon.mas.novica.micronaut.advice;

import fon.mas.novica.micronaut.exception.UsersServiceUnavailableException;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Produces;

@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Requires(classes = {UsersServiceUnavailableException.class, ExceptionHandler.class})
public class UsersServiceUnavailableExceptionHandler
        implements ExceptionHandler<UsersServiceUnavailableException, HttpResponse<ApiException>> {

    @Override
    public HttpResponse<ApiException> handle(HttpRequest request, UsersServiceUnavailableException exception) {
        return HttpResponse.status(HttpStatus.SERVICE_UNAVAILABLE).body(
                new ApiException(HttpStatus.SERVICE_UNAVAILABLE.getCode(),
                        exception.getMessage(),
                        exception.getMessage()));
    }
}