package sdrprojectsmanager.sdr.users;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends CrudRepository<User, Integer> {
    User findByLoginIgnoreCase(String username);

    @Query(value = "SELECT users.* FROM users JOIN teams_squad ON teams_squad.users = users.id WHERE teams_squad.teams = :teamId", nativeQuery = true)
    public List<User> findTeamMembers(@Param("teamId") Integer teamId);
}
