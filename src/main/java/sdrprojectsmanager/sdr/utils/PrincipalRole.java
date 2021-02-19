package sdrprojectsmanager.sdr.utils;

import sdrprojectsmanager.sdr.security.UserPrincipal;

import java.util.HashMap;

import org.springframework.security.core.Authentication;

public class PrincipalRole {

  public static HashMap<String, Object> getFormatedRole(Authentication authentication) {
    UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
    String role = principal.getRole();
    Integer userId = principal.getId();

    HashMap<String, Object> response = new HashMap<String, Object>();

    response.put("role", role.toUpperCase());
    response.put("user_id", userId);

    return response;
  }
}
