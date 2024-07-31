package fon.mas.novica.micronaut.config;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import org.modelmapper.ModelMapper;
import org.modelmapper.record.RecordModule;

@Factory
public class ApplicationConfig {
    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper().registerModule(new RecordModule());
    }
}
