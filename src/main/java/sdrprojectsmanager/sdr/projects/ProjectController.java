package sdrprojectsmanager.sdr.projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import sdrprojectsmanager.sdr.exception.ResourceNotFoundException;
import sdrprojectsmanager.sdr.utils.PrincipalRole;
import sdrprojectsmanager.sdr.utils.ApiResponses.ApiResponse;
import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@ControllerAdvice()
@Valid
@RequestMapping("api/projects")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectController {

    @Autowired
    private ProjectsRepository projectsRepository;

    private EntityManager em;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Project searchResult = projectsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        return ResponseEntity.ok(searchResult);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll(Authentication authentication) {
        Iterable<Project> searchResult;

        if (!"ADMIN".equals(PrincipalRole.getFormatedRole(authentication).get("role"))) {
            Integer id = (int) PrincipalRole.getFormatedRole(authentication).get("user_id");
            searchResult = projectsRepository.findByUser(id);
        } else {
            searchResult = projectsRepository.findAll();
        }

        if (searchResult.equals(null))
            throw new ResourceNotFoundException("Projects not found");

        return ResponseEntity.ok(searchResult);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Object add(@Valid @RequestBody AddProject newProject) {
        Integer projectId;
        try {
            projectId = (Integer) em.createNamedStoredProcedureQuery("CreateProject")
                    .setParameter("proj_name", newProject.getName()).setParameter("team_id", newProject.getTeamId())
                    .setParameter("budget_limit", newProject.getLimitation()).getOutputParameterValue("new_proj_id");
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getCause().getMessage());
        }

        Project project = projectsRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Dane niepoprawne"));

        String message = "Utworzono nowy projekt";

        return ApiResponse.delete(project, message);
    }

    @RequestMapping(value = "endProject/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> endProject(@PathVariable Integer id) {
        Project project = projectsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        project.setState(1);
        projectsRepository.save(project);

        try {
            em.createNamedStoredProcedureQuery("CreateRaport").setParameter("project_id", id).execute();
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getCause().getMessage());
        }

        return ApiResponse.procedure("Project raport has been created");
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Project project = projectsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        try {
            em.createNamedStoredProcedureQuery("DeleteProject").setParameter("input_id", id).execute();
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getCause().getMessage());
        }

        return ApiResponse.delete(project, "Project has been deleted");
    }
}
