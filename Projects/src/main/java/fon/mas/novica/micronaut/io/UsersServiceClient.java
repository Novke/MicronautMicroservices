package fon.mas.novica.micronaut.io;

import fon.mas.novica.micronaut.model.dto.users.UserInfo;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

import java.util.List;

@Client(id = "users-ms")
@ExecuteOn(TaskExecutors.BLOCKING)
public interface UsersServiceClient {

    @Get("/internal/users/{id}")
    UserInfo findUserById(@PathVariable Long id);

    @Post("/internal/users/verify")
    boolean verifyAuthorization(@Body List<Long> ids);

    @Put("/internal/users/{id}/add-task")
    int increaseTaskCount(@PathVariable Long id);
}
