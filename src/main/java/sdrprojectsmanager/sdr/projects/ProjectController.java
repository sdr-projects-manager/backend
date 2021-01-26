package sdrprojectsmanager.sdr.projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdrprojectsmanager.sdr.budgets.Budget;
import sdrprojectsmanager.sdr.budgets.BudgetsRepository;
import sdrprojectsmanager.sdr.exception.ResourceNotFoundException;
import sdrprojectsmanager.sdr.teams.Team;
import sdrprojectsmanager.sdr.teams.TeamsRepository;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@ControllerAdvice()
@Valid
@RequestMapping("api/projects")
public class ProjectController {

    @Autowired
    private ProjectsRepository projectsRepository;
    @Autowired
    private BudgetsRepository budgetsRepository;
    @Autowired
    private TeamsRepository teamsRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Optional<Project> searchResult = projectsRepository.findById(id);
        if (searchResult.isEmpty()) {
            return ResponseEntity.ok("Nie znaleziono");
            // TODO ResponseExceptionController
        }
        return ResponseEntity.ok(searchResult);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        Iterable<Project> searchResult = projectsRepository.findAll();
        if (searchResult.equals(null)) {
            return ResponseEntity.ok("Nie znaleziono");
            // TODO ResponseExceptionController
        }
        return ResponseEntity.ok(searchResult);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Object add(@Valid @RequestBody AddProject newProject){

        Budget newBudget = new Budget();
        try{
            newBudget.setLimitation(newProject.getLimitation());
            newBudget.setCost(0.00);
            budgetsRepository.save(newBudget);
        }
        catch(DataAccessException e){
            throw new ResourceNotFoundException(e.getCause().getMessage());
        }
        Team searchResult = teamsRepository.findById(newProject.getTeamId())
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + newProject.getTeamId()));
        Project project = new Project();
        try{
            project.setName(newProject.getName());
            project.setTeamId(newProject.getTeamId());
            project.setBudget(newBudget);
            project.setState(0);
            projectsRepository.save(project);
        }
        catch(DataAccessException e){
            throw new ResourceNotFoundException(e.getCause().getMessage());
        }
        return ResponseEntity.ok(project);
    }
}
