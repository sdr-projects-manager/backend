package sdrprojectsmanager.sdr.projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sdrprojectsmanager.sdr.users.UsersRepository;

import javax.validation.Valid;

@RestController
@ControllerAdvice()
@Valid
@RequestMapping("api/projects")
public class ProjectController {

    @Autowired
    private ProjectsRepository projectsRepository;

}
