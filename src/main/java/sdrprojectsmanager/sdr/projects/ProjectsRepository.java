package sdrprojectsmanager.sdr.projects;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProjectsRepository extends CrudRepository<Project, Integer> {
  @Query(value = "SELECT projects.* FROM projects JOIN teams_squad ON teams_squad.teams = team_id JOIN users ON teams_squad.users = users.id WHERE users.id = :userId", nativeQuery = true)
  public List<Project> findByUser(@Param("userId") Integer userId);
}
