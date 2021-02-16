package sdrprojectsmanager.sdr.currentUser;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import sdrprojectsmanager.sdr.users.User;
import sdrprojectsmanager.sdr.users.UsersRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/currentUser")
@RequiredArgsConstructor
public class CurrentUserController {

  private final UsersRepository userRepository;

  @RequestMapping(method = RequestMethod.GET)
  public @ResponseBody Object getMe(Authentication authentication) {
    User user = userRepository.findByLoginIgnoreCase(authentication.getName());
    return ResponseEntity.ok(user);
  }
}
