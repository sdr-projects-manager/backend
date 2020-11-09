package sdrprojectsmanager.sdr.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.Optional;

@RestController
@RequestMapping(path = "user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/")
    public @ResponseBody Optional<User> getById(@RequestParam Integer id) {
        return userRepository.findById(id);
    }

    @PostMapping(path = "/add")
    public @ResponseBody String add(@Valid User newUser) {
        User user = new User();
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setLastName(newUser.getLastName());
        user.setPassword(newUser.getPassword());
        userRepository.save(user);
        return "User added";
    }
}
