package fon.mas.novica.micronaut;

import fon.mas.novica.micronaut.model.entity.RoleEntity;
import fon.mas.novica.micronaut.model.entity.UserEntity;
import fon.mas.novica.micronaut.repository.RolesRepository;
import fon.mas.novica.micronaut.repository.UsersRepository;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Application {

    @Inject
    UsersRepository usersRepository;
    @Inject
    RolesRepository rolesRepository;
    Logger log = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }

    @EventListener
    @Transactional
    void databaseInit(StartupEvent event){
        if (rolesRepository.count()==0){
            log.debug("Generating roles...");
            rolesRepository.saveAll(List.of(
                    new RoleEntity(1L, "ROLE_USER"),
                    new RoleEntity(2L, "ROLE_ADMIN")
            ));
        }

        if (usersRepository.count()==0){
            log.debug("Generating users...");
            usersRepository.save(
                    new UserEntity(
                            null,
                            "default@email.com",
                            "name",
                            "lastname",
                            "user",
//                            encoder().encode("pass"),
                            "pass",
                            rolesRepository.findById(2L).orElseThrow(),
                            true));
        }
    }

}