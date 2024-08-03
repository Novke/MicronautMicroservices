package fon.mas.novica.micronaut.advice;

import fon.mas.novica.micronaut.exception.UsernameNotFoundException;
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
@Requires(classes = {UsernameNotFoundException.class, ExceptionHandler.class})
public class UsernameNotFoundExceptionHandler
        implements ExceptionHandler<UsernameNotFoundException, HttpResponse<ApiException>> {

    @Override
    public HttpResponse<ApiException> handle(HttpRequest request, UsernameNotFoundException exception) {
        return HttpResponse.notFound().body(
                new ApiException(HttpStatus.NOT_FOUND.getCode(),
                        exception.getMessage(),
                        "User not found!")
        );
    }
}
