package sdrprojectsmanager.sdr.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdrprojectsmanager.sdr.roles.RolesRepository;
import sdrprojectsmanager.sdr.users.dtos.UserDto;
import javax.validation.*;
import java.util.Optional;

@RestController
@ControllerAdvice()
@Valid
@RequestMapping("api/users")
public class UsersController {

    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private RolesRepository rolesRepository;

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Optional<User> searchResult = userRepository.findById(id);
        if (searchResult.isEmpty()) {
            return ResponseEntity.ok("Ni ma");
            // TODO ResponseExceptionController
        }
        return ResponseEntity.ok(searchResult);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody Object add(@Valid @RequestBody UserDto newUser) {
        System.out.println("Create");
        User user = new User();
        user.setLogin(newUser.getLogin());
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setLastName(newUser.getLastName());
        var role = rolesRepository.findById(newUser.getRole_id());
        if (role != null) {
            user.setRole(role.get());
        }
        // user.setPassword(BCryptPasswordEncoder.encode(newUser.getPassword()));
        user.setPassword(newUser.getPassword());
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }
}
