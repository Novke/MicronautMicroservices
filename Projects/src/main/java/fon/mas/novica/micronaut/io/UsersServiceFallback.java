package fon.mas.novica.micronaut.io;

import fon.mas.novica.micronaut.exception.UsersServiceUnavailableException;
import fon.mas.novica.micronaut.model.dto.users.UserInfo;
import io.micronaut.retry.annotation.Fallback;

import java.util.List;

@Fallback
public class UsersServiceFallback implements UsersServiceClient{
    @Override
    public UserInfo findUserById(Long id) {
        System.out.println("CANT FIND USER");
        throw new UsersServiceUnavailableException("Cant find user, users service is unreachable!");
    }

    @Override
    public boolean verifyAuthorization(List<Long> ids) {
        System.out.println("CANT VERIFY USER");
        throw new UsersServiceUnavailableException("Cant authorize, users service is unreachable!");
    }

    @Override
    public int increaseTaskCount(Long id) {
        System.out.println("CANT INCREASE TASK COUNT");
        throw new UsersServiceUnavailableException("Cant update experience, users service is unreachable!");
    }
}
