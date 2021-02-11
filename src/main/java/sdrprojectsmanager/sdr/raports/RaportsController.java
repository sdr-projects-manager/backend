package sdrprojectsmanager.sdr.raports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdrprojectsmanager.sdr.exception.ResourceNotFoundException;

import javax.validation.Valid;

@RestController
@ControllerAdvice()
@Valid
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/raports")
public class RaportsController {


    @Autowired
    private RaportRepository raportRepository;


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Raport searchResult = raportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Raport not found with id: " + id));
        return ResponseEntity.ok(searchResult);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Object getAll() {
        Iterable <Raport> allUsers = raportRepository.findAll();
        if(allUsers.equals(null)) throw new ResourceNotFoundException("Raports not found");
        return ResponseEntity.ok(allUsers);
    }
}
