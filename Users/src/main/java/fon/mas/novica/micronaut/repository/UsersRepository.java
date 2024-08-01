package fon.mas.novica.micronaut.repository;

import fon.mas.novica.micronaut.model.entity.UserEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findAllByEnabledTrue();
}
