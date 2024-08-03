package fon.mas.novica.micronaut.advice;

import fon.mas.novica.micronaut.exception.UserNotFoundException;
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
@Requires(classes = {UserNotFoundException.class, ExceptionHandler.class})
public class UserNotFoundExceptionHandler
        implements ExceptionHandler<UserNotFoundException, HttpResponse<ApiException>> {

    @Override
    public HttpResponse<ApiException> handle(HttpRequest request, UserNotFoundException exception) {
        return HttpResponse.notFound().body(
                new ApiException(HttpStatus.NOT_FOUND.getCode(),
                        exception.getMessage(),
                        "User does not exist!"));
    }
}
