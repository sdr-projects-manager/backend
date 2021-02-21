package sdrprojectsmanager.sdr.budgets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdrprojectsmanager.sdr.exception.ResourceNotFoundException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@ControllerAdvice()
@Valid
@RequestMapping("api/budgets")
public class BudgetController {

    @Autowired
    private BudgetsRepository budgetsRepository;

    public Budget createNewBudget(Double newLimit) {

        Budget budget = new Budget();
        try {
            budget.setLimitation(newLimit);
            budget.setCost(0.00);
            budgetsRepository.save(budget);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Create team fails");
        }
        return budget;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Optional<Budget> searchResult = budgetsRepository.findById(id);
        if (searchResult.isEmpty()) {
            return ResponseEntity.ok("Nie znaleziono");
            // TODO ResponseExceptionController
        }
        return ResponseEntity.ok(searchResult);
    }

    @RequestMapping(value = "/changeLimit/{budgetId}", method = RequestMethod.POST)
    public @ResponseBody Object setMaxUsers(@PathVariable Integer budgetId, @RequestBody Budget editLimit) {
        Budget budgetEdit = budgetsRepository.findById(budgetId).orElse(null);
        if (budgetEdit == null) {
            return ResponseEntity.ok("Nie znaleziono");
            // TODO ResponseExceptionController
        }
        budgetEdit.setLimitation(editLimit.getLimitation());
        budgetsRepository.save(budgetEdit);

        return ResponseEntity.ok(budgetEdit);
    }
}
