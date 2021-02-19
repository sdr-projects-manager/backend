package sdrprojectsmanager.sdr.teams;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface TeamsSquadRepository extends CrudRepository<TeamSquad, Integer> {

  List<TeamSquad> findByUserId(Integer userId);
}
