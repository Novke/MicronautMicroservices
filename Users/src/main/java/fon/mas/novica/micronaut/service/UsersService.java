package fon.mas.novica.micronaut.service;

import fon.mas.novica.micronaut.model.dto.user.CreateUserCmd;
import fon.mas.novica.micronaut.model.dto.user.UserInfo;

import java.util.List;

public interface UsersService {
    UserInfo createUser(CreateUserCmd user);
    UserInfo createAdmin(CreateUserCmd user);
    List<UserInfo> findActiveUsers();
}
