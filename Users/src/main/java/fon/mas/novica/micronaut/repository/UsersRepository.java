package fon.mas.novica.micronaut.repository;

import fon.mas.novica.micronaut.model.entity.UserEntity;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findAllByEnabledTrue();
    Optional<UserEntity> findByUsername(String username);
    @Query("SELECT u.id FROM UserEntity u WHERE u.username = :username")
    Long findIdByUsername(String username);
}
