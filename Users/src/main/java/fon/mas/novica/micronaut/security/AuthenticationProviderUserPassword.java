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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Singleton
public class AuthenticationProviderUserPassword implements AuthenticationProvider {

    @Inject
    private UsersRepository usersRepository;
    @Inject
    private PasswordEncoder passwordEncoder;

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public @NonNull AuthenticationResponse authenticate(Object requestContext, @NonNull AuthenticationRequest authRequest) {

        String identity = (String) authRequest.getIdentity();
        String secret = (String) authRequest.getSecret();

        log.debug("User {} tries to login...", identity);

        Optional<UserEntity> optionalUser = usersRepository.findByUsername(identity);

        if (optionalUser.isPresent()) {
            log.debug("Found user!");

            HashMap<String, Object> attributes = new HashMap<>();
            attributes.put("key", "value");

            UserEntity user = optionalUser.get();
            if (passwordEncoder.matches(secret, user.getPassword())){
                return AuthenticationResponse.success(identity, List.of(user.getRole().getName()), attributes);
            } else {
                log.debug("Wrong password");
                return AuthenticationResponse.failure("Wrong password");
            }
        }

        log.debug("No such user!");

        return AuthenticationResponse.failure(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH);
    }
}
