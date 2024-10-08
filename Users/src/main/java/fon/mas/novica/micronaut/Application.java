package fon.mas.novica.micronaut;

import fon.mas.novica.micronaut.model.entity.RoleEntity;
import fon.mas.novica.micronaut.model.entity.UserEntity;
import fon.mas.novica.micronaut.repository.RolesRepository;
import fon.mas.novica.micronaut.repository.UsersRepository;
import io.micronaut.context.annotation.Value;
import io.micronaut.context.env.Environment;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class Application {

    @Inject
    UsersRepository usersRepository;
    @Inject
    RolesRepository rolesRepository;
    @Inject
    PasswordEncoder passwordEncoder;
    @Inject
    Environment environment;
    @Value("${config.source}")
    String configSource;
    Logger log = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }


    @EventListener
    void check(StartupEvent event){
        log.info("======= CHECKING CONFIG SERVER =======");
        log.info("Config source: {}", configSource);
        log.info("======================================");
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
            usersRepository.saveAll(List.of(
                    new UserEntity(
                            null,
                            "default@email.com",
                            "name",
                            "lastname",
                            "user",
                            passwordEncoder.encode("pass"),
                            rolesRepository.findById(1L).orElseThrow(),
                            true),
                    new UserEntity(
                            null,
                            "admin@email.com",
                            "Novica",
                            "Trifkovic",
                            "admin",
                            passwordEncoder.encode("admin"),
                            rolesRepository.findById(2L).orElseThrow(),
                            true)
            ));
        }
    }

}