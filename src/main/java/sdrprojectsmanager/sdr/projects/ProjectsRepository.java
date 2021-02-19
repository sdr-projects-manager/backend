package sdrprojectsmanager.sdr.projects;

import org.springframework.data.repository.CrudRepository;
import sdrprojectsmanager.sdr.users.User;

import java.util.List;

public interface ProjectsRepository extends CrudRepository<Project, Integer> {
}
