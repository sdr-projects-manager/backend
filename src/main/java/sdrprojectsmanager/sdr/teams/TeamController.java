package sdrprojectsmanager.sdr.teams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@ControllerAdvice()
@Valid
@RequestMapping("api/teams")
public class TeamController {

    @Autowired
    private TeamsRepository teamsRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Optional<Team> searchResult = teamsRepository.findById(id);
        if (searchResult.isEmpty()) {
            return ResponseEntity.ok("Nie znaleziono");
            // TODO ResponseExceptionController
        }
        return ResponseEntity.ok(searchResult);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        Iterable<Team> searchResult = teamsRepository.findAll();
        if (searchResult.equals(null)) {
            return ResponseEntity.ok("Nie znaleziono");
            // TODO ResponseExceptionController
        }
        return ResponseEntity.ok(searchResult);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Object add(@Valid @RequestBody Team newTeam) {
        System.out.println("Create");
        Team team = new Team();
        team.setName(newTeam.getName());
        team.setMaxPeople(newTeam.getMaxPeople());
        teamsRepository.save(team);
        return ResponseEntity.ok(team);
    }

    @RequestMapping(value = "/setMaxUsers/{teamId}", method = RequestMethod.POST)
    public @ResponseBody Object setMaxUsers(@PathVariable Integer teamId, @RequestBody Team editTeam) {
        Team teamEdit = teamsRepository.findById(teamId).orElse(null);
        if (teamEdit == null) {
            return ResponseEntity.ok("Nie znaleziono");
            // TODO ResponseExceptionController
        }
        System.out.println(teamId);
        System.out.println(editTeam.getMaxPeople());
        teamEdit.setMaxPeople(editTeam.getMaxPeople());
        teamsRepository.save(teamEdit);
        return ResponseEntity.ok(teamEdit);
    }
}
