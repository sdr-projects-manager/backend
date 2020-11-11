package sdrprojectsmanager.sdr.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.Optional;

@RestController
@ControllerAdvice()
@Valid
@RequestMapping("api/users")
public class UsersController {

    @Autowired
    private UsersRepository userRepository;

    @GetMapping(value = "/")
    public @ResponseBody Optional<User> getById(@RequestParam Integer id) {
        return userRepository.findById(id);
    }

    @PostMapping()
    public @ResponseBody Object add(@Valid @RequestBody User newUser) {
       User user = new User();
       user.setName(newUser.getName());
       user.setEmail(newUser.getEmail());
       user.setLastName(newUser.getLastName());
       user.setPassword(newUser.getPassword());
       userRepository.save(user);
       return user;
    }
}
