package fon.mas.novica.micronaut.advice;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Produces;

@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Requires(classes = {HttpClientResponseException.class, ExceptionHandler.class})
public class HttpClientResponseExceptionHandler
        implements ExceptionHandler<HttpClientResponseException, HttpResponse<ApiException>> {

    @Override
    public HttpResponse<ApiException> handle(HttpRequest request, HttpClientResponseException exception) {
        return HttpResponse.status(exception.getStatus()).body(
                new ApiException(exception.getStatus().getCode(),
                        exception.getMessage(),
                        exception.getMessage()));
    }
}
