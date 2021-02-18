package sdrprojectsmanager.sdr.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdrprojectsmanager.sdr.exception.ResourceNotFoundException;
import sdrprojectsmanager.sdr.utils.ApiResponses.ApiResponse;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
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

    @Autowired
    private EntityManager em;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Object getAll() {
        Iterable<Task> allTasks = taskRepository.findAll();
        if (allTasks.equals(null))
            throw new ResourceNotFoundException("Tasls not found");
        return ResponseEntity.ok(allTasks);
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

    @Procedure(name = "AddTask")
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Object add(@Valid @RequestBody Task newTask) {
        try {
            em.createNamedStoredProcedureQuery("AddTask")
                    .setParameter("task_name", newTask.getName())
                    .setParameter("task_description", newTask.getDescription())
                    .setParameter("user_id", newTask.getUserId())
                    .setParameter("project_id", newTask.getProjectId())
                    .setParameter("task_cost", newTask.getCost()).execute();
        }
        catch(Exception e){
            throw new ResourceNotFoundException(e.getCause().getMessage());
        }
        return ApiResponse.procedure("Task has been created successfuly");
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
