package fon.mas.novica.micronaut.security;

import fon.mas.novica.micronaut.model.entity.UserEntity;
import fon.mas.novica.micronaut.repository.UsersRepository;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.security.authentication.AuthenticationFailureReason;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.provider.AuthenticationProvider;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Singleton
public class AuthenticationProviderUserPassword implements AuthenticationProvider {

    @Inject
    private UsersRepository usersRepository;

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public @NonNull AuthenticationResponse authenticate(Object requestContext, @NonNull AuthenticationRequest authRequest) {

        String identity = (String) authRequest.getIdentity();
        String secret = (String) authRequest.getSecret();

        log.info("User {} tries to login...", identity);

        Optional<UserEntity> optionalUser = usersRepository.findByUsername(identity);

        if (optionalUser.isPresent()) {
            log.info("Found user!");

            HashMap<String, Object> attributes = new HashMap<>();
            attributes.put("key", "value");

            UserEntity user = optionalUser.get();
            if (user.getPassword().equals(secret)){
                return AuthenticationResponse.success(identity, List.of(user.getRole().getName()), attributes);
            } else {
                log.info("Wrong password");
                return AuthenticationResponse.failure("Wrong password");
            }
        }

        log.info("No such user!");

        return AuthenticationResponse.failure(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH);
    }
}
