package fon.mas.novica.micronaut.repository;

import fon.mas.novica.micronaut.model.entity.ProjectEntity;
import fon.mas.novica.micronaut.model.enums.Status;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ProjectsRepository extends JpaRepository<ProjectEntity, Long> {

    List<ProjectEntity> findAllByStatusNotEquals(Status status);
}
