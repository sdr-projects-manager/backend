package sdrprojectsmanager.sdr.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import sdrprojectsmanager.sdr.exception.ResourceNotFoundException;
import sdrprojectsmanager.sdr.security.UserPrincipal;
import sdrprojectsmanager.sdr.utils.PrincipalRole;
import sdrprojectsmanager.sdr.utils.ApiResponses.ApiResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@ControllerAdvice()
@Valid
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Object getAll(Authentication authentication) {

        Iterable<Task> searchResult;

        if (PrincipalRole.getFormatedRole(authentication).get("role") != "ADMIN") {
            searchResult = taskRepository
                    .findByUserId((int) PrincipalRole.getFormatedRole(authentication).get("user_id"));
        } else {
            searchResult = taskRepository.findAll();
        }

        if (searchResult.equals(null))
            throw new ResourceNotFoundException("Tasks not found");

        return ResponseEntity.ok(searchResult);
    }

    @RequestMapping(value = "/taskInProject/{projectId}", method = RequestMethod.GET)
    public List<Task> getTaskInProject(@PathVariable Integer projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    @RequestMapping(value = "/userTask/{userId}", method = RequestMethod.GET)
    public List<Task> getUserTask(@PathVariable Integer userId) {
        return taskRepository.findByUserId(userId);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Task searchResult = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        return ResponseEntity.ok(searchResult);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Object add(@Valid @RequestBody AddTask newTask) {
        Task task = new Task();
        try {
            task.setName(newTask.getName());
            task.setDescription(newTask.getDescription());
            task.setCost(newTask.getCost());
            task.setState(0);
            task.setProjectId(newTask.getProjectId());
            task.setUserId(newTask.getUserId());
        } catch (DataAccessException e) {
            throw new ResourceNotFoundException(e.getCause().getMessage());
        }
        taskRepository.save(task);
        return ResponseEntity.ok(task);
    }

    @RequestMapping(value = "/endTask/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> endTask(@PathVariable Integer id) {
        Task searchResult = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        searchResult.setState(1);
        taskRepository.save(searchResult);
        return ResponseEntity.ok(searchResult);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> edit(@PathVariable Integer id, @RequestBody AddTask editTask) {
        Task searchResult = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        if (!editTask.getName().isEmpty()) {
            searchResult.setName(editTask.getName());
        }

        if (!editTask.getDescription().isEmpty()) {
            searchResult.setDescription(editTask.getDescription());
        }

        if (editTask.getCost() > 0) {
            searchResult.setCost(editTask.getCost());
        }

        taskRepository.save(searchResult);

        return ResponseEntity.ok(searchResult);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        taskRepository.deleteById(id);

        return ApiResponse.delete(task, "Task has been deleted");
    }
}
