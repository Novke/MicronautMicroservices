package fon.mas.novica.micronaut;

import io.micronaut.context.annotation.Value;
import io.micronaut.context.env.Environment;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

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
}