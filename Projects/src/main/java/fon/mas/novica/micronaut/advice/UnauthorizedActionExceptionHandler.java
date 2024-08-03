package fon.mas.novica.micronaut.advice;

import fon.mas.novica.micronaut.exception.UnauthorizedActionException;
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
@Requires(classes = {UnauthorizedActionException.class, ExceptionHandler.class})
public class UnauthorizedActionExceptionHandler
        implements ExceptionHandler<UnauthorizedActionException, HttpResponse<ApiException>> {

    @Override
    public HttpResponse<ApiException> handle(HttpRequest request, UnauthorizedActionException exception) {
        return HttpResponse.unauthorized().body(
                new ApiException(HttpStatus.UNAUTHORIZED.getCode(),
                        exception.getMessage(),
                        "UNAUTHORIZED ACTION!"));
    }
}
