package fon.mas.novica.micronaut.service;

import fon.mas.novica.micronaut.model.dto.user.CreateUserCmd;
import fon.mas.novica.micronaut.model.dto.user.UpdatePasswordCmd;
import fon.mas.novica.micronaut.model.dto.user.UserInfo;
import fon.mas.novica.micronaut.model.dto.user.UserInsight;
import io.micronaut.security.authentication.Authentication;

import java.util.List;

public interface UsersService {
    UserInfo createUser(CreateUserCmd user);
    UserInfo createAdmin(CreateUserCmd user);
    List<UserInfo> findActiveUsers();
    List<UserInsight> findAllUsers();
    void enableUser(String user);
    void disableUser(String user);
    void updatePassword(UpdatePasswordCmd cmd);
    public UserInsight findById(Long id);
    public Boolean checkAuthorization(Authentication authentication, List<Long> ids);
    Integer increaseTaskCount(Long id);
}
