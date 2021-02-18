package sdrprojectsmanager.sdr.utils.ApiResponses;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;

public class ApiResponse {

  public static ResponseEntity<?> delete(Object object, String message) {
    HashMap<String, Object> response = new HashMap<String, Object>();

    response.put("instance", object);
    response.put("message", message);

    return ResponseEntity.ok(response);
  }

  public static ResponseEntity<?> procedure(String message) {
    HashMap<String, Object> response = new HashMap<String, Object>();

    response.put("message", message);

    return ResponseEntity.ok(response);
  }
}
