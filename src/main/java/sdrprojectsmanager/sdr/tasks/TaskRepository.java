package sdrprojectsmanager.sdr.tasks;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Integer> {

    List<Task> findByProjectId(Integer id);

    List<Task> findByUserId(Integer userId);
}
