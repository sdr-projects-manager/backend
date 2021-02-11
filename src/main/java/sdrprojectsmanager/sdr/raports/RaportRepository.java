package sdrprojectsmanager.sdr.raports;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RaportRepository extends CrudRepository<Raport, Integer> {
    Raport findByProjectId(Integer id);
}
