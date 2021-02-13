package sdrprojectsmanager.sdr.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdrprojectsmanager.sdr.exception.ResourceNotFoundException;

import javax.validation.Valid;

@RestController
@ControllerAdvice()
@Valid
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/roles")
public class RoleController {


    @Autowired
    private RolesRepository rolesRepository;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody    Object getAll() {
        Iterable<Role> allRoles = rolesRepository.findAll();
        if (allRoles.equals(null))
            throw new ResourceNotFoundException("Roles not found");
        return ResponseEntity.ok(allRoles);
    }
}
