package sdrprojectsmanager.sdr.projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@ControllerAdvice()
@Valid
@RequestMapping("api/projects")
public class ProjectController {

    @Autowired
    private ProjectsRepository projectsRepository;

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, consumes = "application/json")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Optional<Project> searchResult = projectsRepository.findById(id);
        if (searchResult.isEmpty()) {
            return ResponseEntity.ok("Nie znaleziono");
            // TODO ResponseExceptionController
        }
        return ResponseEntity.ok(searchResult);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, consumes = "application/json")
    public ResponseEntity<?> getAll() {
        Iterable<Project> searchResult = projectsRepository.findAll();
        if (searchResult.equals(null)) {
            return ResponseEntity.ok("Nie znaleziono");
            // TODO ResponseExceptionController
        }
        return ResponseEntity.ok(searchResult);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody Object add(@Valid @RequestBody Project newProject) {
        System.out.println("Create");
        Project project = new Project();
        project.setName(newProject.getName());
        project.setTeamId(newProject.getTeamId());
        project.setBudgetId(1);
        project.setState(0);
        projectsRepository.save(project);
        return ResponseEntity.ok(project);
    }
}
