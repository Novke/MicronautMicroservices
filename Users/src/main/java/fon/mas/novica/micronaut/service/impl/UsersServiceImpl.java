package fon.mas.novica.micronaut.service.impl;

import fon.mas.novica.micronaut.model.dto.user.CreateUserCmd;
import fon.mas.novica.micronaut.model.dto.user.UserInfo;
import fon.mas.novica.micronaut.model.entity.UserEntity;
import fon.mas.novica.micronaut.repository.RolesRepository;
import fon.mas.novica.micronaut.repository.UsersRepository;
import fon.mas.novica.micronaut.service.UsersService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.modelmapper.ModelMapper;

import java.util.List;

@Singleton
public class UsersServiceImpl implements UsersService {

    @Inject
    UsersRepository usersRepository;
    @Inject
    RolesRepository rolesRepository;
    @Inject
    ModelMapper mapper;

    @Override
    public UserInfo createUser(CreateUserCmd user) {
        UserEntity userRequest = mapper.map(user, UserEntity.class);
        userRequest.setRole(rolesRepository.findById(1L).orElseThrow());
//        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        UserEntity createdUser = usersRepository.save(userRequest);
        return mapper.map(createdUser, UserInfo.class);
    }

    @Override
    public UserInfo createAdmin(CreateUserCmd user) {
        UserEntity userRequest = mapper.map(user, UserEntity.class);
        userRequest.setRole(rolesRepository.findById(2L).orElseThrow());
//        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        UserEntity createdUser = usersRepository.save(userRequest);
        return mapper.map(createdUser, UserInfo.class);
    }

    @Override
    public List<UserInfo> findActiveUsers() {
        return usersRepository.findAllByEnabledTrue().stream()
                .map(u -> mapper.map(u, UserInfo.class))
                .toList();
    }
}
