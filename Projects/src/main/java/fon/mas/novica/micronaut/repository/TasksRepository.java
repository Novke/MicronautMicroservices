package fon.mas.novica.micronaut.repository;

import fon.mas.novica.micronaut.model.entity.TaskEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface TasksRepository extends JpaRepository<TaskEntity, Long> {
}
