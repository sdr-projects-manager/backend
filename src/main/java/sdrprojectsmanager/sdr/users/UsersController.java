package sdrprojectsmanager.sdr.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sdrprojectsmanager.sdr.exception.ResourceNotFoundException;
import sdrprojectsmanager.sdr.roles.RolesRepository;
import sdrprojectsmanager.sdr.users.dtos.UserDto;
import javax.validation.*;

@RestController
@ControllerAdvice()
@Validated
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/users")
public class UsersController {

    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    PasswordEncoder encoder;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        User searchResult = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return ResponseEntity.ok(searchResult);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Object getAll() {
        Iterable<User> allUsers = userRepository.findAll();
        if (allUsers.equals(null))
            throw new ResourceNotFoundException("Users not found");
        return ResponseEntity.ok(allUsers);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Object add(@Validated @RequestBody UserDto newUser) {
        User user = new User();
        try {
            user.setLogin(newUser.getLogin());
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            user.setLastName(newUser.getLastName());
            var role = rolesRepository.findById(newUser.getRole_id());
            if (role != null) {
                user.setRole(role.get());
            }
            user.setPassword(encoder.encode(newUser.getPassword()));
            userRepository.save(user);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Role not found");
        }

        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public @ResponseBody Object edit(@Valid @RequestBody UserDto newEdit, @PathVariable Integer id) {
        User edit = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        try {
            edit.setLogin(newEdit.getLogin());
            edit.setName(newEdit.getName());
            edit.setEmail(newEdit.getEmail());
            edit.setLastName(newEdit.getLastName());
            var role = rolesRepository.findById(newEdit.getRole_id());
            if (role != null)
                edit.setRole(role.get());
            edit.setPassword(encoder.encode(newEdit.getPassword()));
            userRepository.save(edit);
        } catch (DataAccessException e) {
            throw new ResourceNotFoundException(e.getCause().getMessage());
        }
        return ResponseEntity.ok(edit);
    }
}
