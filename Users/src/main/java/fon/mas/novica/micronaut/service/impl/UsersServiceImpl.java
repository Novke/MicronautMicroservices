package fon.mas.novica.micronaut.service.impl;

import fon.mas.novica.micronaut.exception.BadCredentialsException;
import fon.mas.novica.micronaut.exception.UserAlreadyDisabledException;
import fon.mas.novica.micronaut.exception.UserAlreadyEnabledException;
import fon.mas.novica.micronaut.exception.UsernameNotFoundException;
import fon.mas.novica.micronaut.model.dto.user.CreateUserCmd;
import fon.mas.novica.micronaut.model.dto.user.UpdatePasswordCmd;
import fon.mas.novica.micronaut.model.dto.user.UserInfo;
import fon.mas.novica.micronaut.model.dto.user.UserInsight;
import fon.mas.novica.micronaut.model.entity.UserEntity;
import fon.mas.novica.micronaut.repository.RolesRepository;
import fon.mas.novica.micronaut.repository.UsersRepository;
import fon.mas.novica.micronaut.service.UsersService;
import io.micronaut.security.authentication.Authentication;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Singleton
public class UsersServiceImpl implements UsersService {

    @Inject
    UsersRepository usersRepository;
    @Inject
    RolesRepository rolesRepository;
    @Inject
    ModelMapper mapper;
    @Inject
    PasswordEncoder passwordEncoder;

    @Override
    public UserInfo createUser(CreateUserCmd user) {
        UserEntity userRequest = mapper.map(user, UserEntity.class);
        userRequest.setRole(rolesRepository.findById(1L).orElseThrow());
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        UserEntity createdUser = usersRepository.save(userRequest);
        return mapper.map(createdUser, UserInfo.class);
    }

    @Override
    public UserInfo createAdmin(CreateUserCmd user) {
        UserEntity userRequest = mapper.map(user, UserEntity.class);
        userRequest.setRole(rolesRepository.findById(2L).orElseThrow());
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        UserEntity createdUser = usersRepository.save(userRequest);
        return mapper.map(createdUser, UserInfo.class);
    }

    @Override
    public List<UserInsight> findAllUsers() {
        return usersRepository.findAll().stream()
                .map(u -> mapper.map(u, UserInsight.class))
                .toList();
    }

    @Override
    public List<UserInfo> findActiveUsers() {
        return usersRepository.findAllByEnabledTrue().stream()
                .map(u -> mapper.map(u, UserInfo.class))
                .toList();
    }

    @Override
    public void enableUser(String username) {
        UserEntity user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with username %s does not exist", username)));
        if (user.isEnabled()) throw new UserAlreadyEnabledException();
        user.setEnabled(true);
        usersRepository.save(user);
    }

    @Override
    public void disableUser(String username) {
        UserEntity user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with username %s does not exist", username)));
        if (!user.isEnabled()) throw new UserAlreadyDisabledException();
        user.setEnabled(false);
        usersRepository.save(user);
    }

    @Override
    public void updatePassword(UpdatePasswordCmd cmd) {
        UserEntity user = usersRepository.findByUsername(cmd.username())
                .orElseThrow(() -> new UsernameNotFoundException("Wrong username or password"));
        if (passwordEncoder.matches(cmd.confirmPassword(), user.getPassword())){
            user.setPassword(passwordEncoder.encode(cmd.newPassword()));
            usersRepository.save(user);
        } else {
            throw new BadCredentialsException("Wrong username or password");
        }
    }


    @Override
    public UserInsight findById(Long id) {
        return mapper.map(
                usersRepository.findById(id)
                        .orElseThrow(() -> new UsernameNotFoundException("No user with id " + id)),
                UserInsight.class);
    }

    @Override
    public Boolean checkAuthorization(Authentication authentication, List<Long> ids) {
        String issuerUsername = authentication.getName();
        Long issuerId = usersRepository.findIdByUsername(issuerUsername);
        if (issuerId == null) return false;

        return ids.contains(issuerId);
    }
}
