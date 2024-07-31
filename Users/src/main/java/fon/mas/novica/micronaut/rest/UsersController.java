package fon.mas.novica.micronaut.rest;

import fon.mas.novica.micronaut.model.dto.user.CreateUserCmd;
import fon.mas.novica.micronaut.model.entity.UserEntity;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.modelmapper.ModelMapper;

@Controller("/users")
@Singleton
public class UsersController {

    @Inject
    ModelMapper mapper;
    @Post
    @Produces(MediaType.TEXT_PLAIN)
    public Response createUser(@Body CreateUserCmd cmd){
        UserEntity user = mapper.map(cmd, UserEntity.class);

        return Response.ok(user.toString()).build();
    }
}
