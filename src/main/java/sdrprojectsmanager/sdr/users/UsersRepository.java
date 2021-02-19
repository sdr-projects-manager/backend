package sdrprojectsmanager.sdr.users;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Integer> {
    User findByLoginIgnoreCase(String username);
}
