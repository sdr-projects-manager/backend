package sdrprojectsmanager.sdr.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdrprojectsmanager.sdr.exception.ResourceNotFoundException;

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

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> editTask(@PathVariable Integer id, @RequestBody AddTask editTask) {
        Task searchResult = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        searchResult.setName(editTask.getName());
        searchResult.setDescription(editTask.getName());
        searchResult.setCost(editTask.getCost());
        searchResult.setUserId(editTask.getUserId());
        taskRepository.save(searchResult);
        return ResponseEntity.ok(searchResult);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> editTask(@PathVariable Integer id) {
        taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        taskRepository.deleteById(id);

        return ResponseEntity.ok("Task has been deleted");
    }
}
