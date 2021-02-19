package sdrprojectsmanager.sdr.tasks;

import org.springframework.data.repository.CrudRepository;
import sdrprojectsmanager.sdr.projects.Project;
import sdrprojectsmanager.sdr.users.User;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Integer> {

    List<Task> findByProjectIdAndId(Project projectId, Integer projectId1);

    List<Task> findByUserId(User user);
}
