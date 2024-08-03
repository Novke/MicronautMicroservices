package fon.mas.novica.micronaut.advice;

import fon.mas.novica.micronaut.exception.ProjectNotFoundException;
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
@Requires(classes = {ProjectNotFoundException.class, ExceptionHandler.class})
public class ProjectNotFoundExceptionHandler
        implements ExceptionHandler<ProjectNotFoundException, HttpResponse<ApiException>> {

    @Override
    public HttpResponse<ApiException> handle(HttpRequest request, ProjectNotFoundException exception) {
        return HttpResponse.notFound().body(
                new ApiException(HttpStatus.NOT_FOUND.getCode(),
                        exception.getMessage(),
                        "Project does not exist!"));
    }
}
