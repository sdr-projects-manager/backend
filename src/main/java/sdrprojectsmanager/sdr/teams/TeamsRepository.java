package sdrprojectsmanager.sdr.teams;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TeamsRepository extends CrudRepository<Team, Integer> {
  @Query(value = "SELECT teams.* FROM teams JOIN teams_squad ON teams_squad.teams= teams.id JOIN users ON teams_squad.users= users.id WHERE users.id = :userId", nativeQuery = true)
  public List<Team> findByUser(@Param("userId") Integer userId);
}
