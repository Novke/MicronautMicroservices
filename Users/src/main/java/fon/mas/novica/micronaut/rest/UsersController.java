package fon.mas.novica.micronaut.rest;

import fon.mas.novica.micronaut.model.dto.user.CreateUserCmd;
import fon.mas.novica.micronaut.service.UsersService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.core.Response;
import org.modelmapper.ModelMapper;

@Controller("/users")
@Singleton
public class UsersController {

    @Inject
    ModelMapper mapper;
    @Inject
    UsersService usersService;

    @Post
    public Response createUser(@Body CreateUserCmd cmd){
        return Response.status(Response.Status.CREATED)
                .entity(usersService.createUser(cmd))
                .build();
    }


    @Get
    public Response getActiveUsers(){
        return Response.ok(usersService.findActiveUsers()).build();
    }
    @Get("/all")
    public Response getAllUsers(){
        return Response.ok(usersService.findAllUsers()).build();
    }
}
