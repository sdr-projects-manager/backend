package sdrprojectsmanager.sdr.teams;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TeamsTaskRepository extends CrudRepository<TeamTask, Integer> {

  List<Team> findByUserId(Integer userId);
}
