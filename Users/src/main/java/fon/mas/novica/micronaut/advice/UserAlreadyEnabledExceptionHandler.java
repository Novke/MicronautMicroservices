package fon.mas.novica.micronaut.advice;

import fon.mas.novica.micronaut.exception.UserAlreadyDisabledException;
import fon.mas.novica.micronaut.exception.UserAlreadyEnabledException;
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
@Requires(classes = {UserAlreadyEnabledException.class, ExceptionHandler.class})
public class UserAlreadyEnabledExceptionHandler
        implements ExceptionHandler<UserAlreadyEnabledException, HttpResponse<ApiException>> {

    @Override
    public HttpResponse<ApiException> handle(HttpRequest request, UserAlreadyEnabledException exception) {
        return HttpResponse.badRequest().body(
                new ApiException(HttpStatus.BAD_REQUEST.getCode(),
                        exception.getMessage(),
                        "User already enabled!")
        );
    }
}
