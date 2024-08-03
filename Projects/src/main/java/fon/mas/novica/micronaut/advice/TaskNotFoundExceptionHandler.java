package fon.mas.novica.micronaut.advice;

import fon.mas.novica.micronaut.exception.TaskNotFoundException;
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
@Requires(classes = {TaskNotFoundException.class, ExceptionHandler.class})
public class TaskNotFoundExceptionHandler
        implements ExceptionHandler<TaskNotFoundException, HttpResponse<ApiException>> {

    @Override
    public HttpResponse<ApiException> handle(HttpRequest request, TaskNotFoundException exception) {
        return HttpResponse.notFound().body(
                new ApiException(HttpStatus.NOT_FOUND.getCode(),
                        exception.getMessage(),
                        "Task does not exist!"));
    }
}
