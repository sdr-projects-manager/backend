package sdrprojectsmanager.sdr.raports;

import org.springframework.data.repository.CrudRepository;

public interface RaportRepository extends CrudRepository<Raport, Integer> {
    Raport findByProjectId(Integer id);
}
