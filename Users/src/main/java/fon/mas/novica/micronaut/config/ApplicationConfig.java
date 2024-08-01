package fon.mas.novica.micronaut.config;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.record.RecordModule;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Factory
public class ApplicationConfig {
    @Bean
    ModelMapper modelMapper(){
        ModelMapper mapper =  new ModelMapper().registerModule(new RecordModule());
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
