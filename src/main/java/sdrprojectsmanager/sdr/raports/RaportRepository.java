package sdrprojectsmanager.sdr.raports;

import org.springframework.data.repository.CrudRepository;
import sdrprojectsmanager.sdr.projects.Project;

public interface RaportRepository extends CrudRepository<Raport, Integer> {
    Raport findByProjectId(Project id);
}
