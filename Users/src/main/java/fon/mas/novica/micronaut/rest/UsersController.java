package fon.mas.novica.micronaut.rest;

import fon.mas.novica.micronaut.model.dto.user.CreateUserCmd;
import fon.mas.novica.micronaut.model.dto.user.UpdatePasswordCmd;
import fon.mas.novica.micronaut.service.UsersService;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.core.Response;

@Controller("/users")
@Singleton
@Secured(SecurityRule.IS_AUTHENTICATED)
public class UsersController {

    @Inject
    UsersService usersService;

    @Post
    @Secured("ROLE_ADMIN")
    public Response createUser(@Body CreateUserCmd cmd){
        return Response.status(Response.Status.CREATED).entity(usersService.createUser(cmd)).build();
    }
    @Post("/admin")
    @Secured("ROLE_ADMIN")
    public Response createAdmin(@Body CreateUserCmd cmd){
        return Response.status(Response.Status.CREATED).entity(usersService.createAdmin(cmd)).build();
    }
    @Get
    public Response getActiveUsers(){
        return Response.ok(usersService.findActiveUsers()).build();
    }
    @Get("/all")
    @Secured("ROLE_ADMIN")
    public Response getAllUsers(){
        return Response.ok(usersService.findAllUsers()).build();
    }

    @Delete("/{user}")
    @Secured("ROLE_ADMIN")
    public Response disableUser(@PathVariable String user){
        usersService.disableUser(user);
        return Response.accepted().build();
    }
    @Patch("/{user}")
    @Secured("ROLE_ADMIN")
    public Response enableUser(@PathVariable String user){
        usersService.enableUser(user);
        return Response.accepted().build();
    }

    @Put
    @Secured("ROLE_ADMIN")
    public Response updatePassword(@Body UpdatePasswordCmd cmd){
        usersService.updatePassword(cmd);
        return Response.accepted().build();
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/hello")
    public Response hello() {
        return Response.ok("Hello!").build();
    }
}
