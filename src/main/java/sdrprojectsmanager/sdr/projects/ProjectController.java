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
import sdrprojectsmanager.sdr.utils.ApiResponses.ApiResponse;

import javax.validation.Valid;

@RestController
@ControllerAdvice()
@Valid
@RequestMapping("api/projects")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectController {

    @Autowired
    private BudgetsRepository budgetsRepository;
    @Autowired
    private TeamsRepository teamsRepository;
    @Autowired
    private ProjectsRepository projectsRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Project searchResult = projectsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        return ResponseEntity.ok(searchResult);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        Iterable<Project> searchResult = projectsRepository.findAll();
        if (searchResult.equals(null))
            throw new ResourceNotFoundException("Project not found");
        return ResponseEntity.ok(searchResult);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Object add(@Valid @RequestBody AddProject newProject) {

        Budget newBudget = new Budget();
        Team searchResult = teamsRepository.findById(newProject.getTeamId())
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + newProject.getTeamId()));
        Project project = new Project();
        try {
            newBudget.setLimitation(newProject.getLimitation());
            newBudget.setCost(0.00);
            budgetsRepository.save(newBudget);
            project.setName(newProject.getName());
            project.setTeamId(searchResult.getId());
            project.setBudget(newBudget);
            project.setState(0);
            projectsRepository.save(project);
        } catch (DataAccessException e) {
            throw new ResourceNotFoundException(e.getCause().getMessage());
        }
        return ResponseEntity.ok(project);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> editTask(@PathVariable Integer id) {
        Project project = projectsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        projectsRepository.deleteById(id);

        return ApiResponse.delete(project, "Project has been deleted");
    }
}
