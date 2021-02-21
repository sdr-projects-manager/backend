package sdrprojectsmanager.sdr.tasks;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import sdrprojectsmanager.sdr.projects.Project;
import sdrprojectsmanager.sdr.users.User;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Integer> {

    List<Task> findByProject(Project projectId);

    List<Task> findByUserId(User user);

    @Query(value = "SELECT tasks.* FROM tasks JOIN raports ON raports.project_id = tasks.project_id WHERE raports.id = :raportId", nativeQuery = true)
    public List<Task> findByRaportId(@Param("raportId") Integer raportId);
}
