package fon.mas.novica.micronaut.io;

import fon.mas.novica.micronaut.service.UsersService;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller("/internal/users")
@Secured(SecurityRule.IS_ANONYMOUS)
public record UsersIO (UsersService usersService) {
    @Get("{id}")
    public Response getUserById(@PathVariable Long id){
        Logger log = LoggerFactory.getLogger(this.getClass());
        log.info("GET USER RECIEVED");
        return Response.ok(usersService.findById(id)).build();
    }

    @Post("/verify")
    public Response checkUser(
            Authentication authentication,
            @Body List<Long> ids){
        Logger log = LoggerFactory.getLogger(this.getClass());
        log.info("CHECK USER RECIEVED");
        return Response.ok(usersService.checkAuthorization(authentication, ids)).build();
//        return Response.ok(true).build();
    }

    @Put("/{id}/add-task")
    public Response increaseTaskCount(@PathVariable Long id){
        return Response.ok(usersService.increaseTaskCount(id)).build();
    }
}
