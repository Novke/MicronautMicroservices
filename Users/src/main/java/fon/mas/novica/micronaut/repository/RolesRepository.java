package fon.mas.novica.micronaut.repository;

import fon.mas.novica.micronaut.model.entity.RoleEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface RolesRepository extends JpaRepository<RoleEntity, Long> {
}
