package sdrprojectsmanager.sdr.currentUser;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/currentUser")
@RequiredArgsConstructor
public class CurrentUserController {

  @RequestMapping(method = RequestMethod.GET)
  public @ResponseBody Object getMe() {
    return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
  }
}
