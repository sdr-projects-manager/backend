package sdrprojectsmanager.sdr.budgets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;

@RestController
@ControllerAdvice()
@Valid
@RequestMapping("api/budgets")
public class BudgetController {

    @Autowired
    private BudgetsRepository budgetsRepository;

    public Integer createNewBudget(BigDecimal newLimit) {
        Budget budget = new Budget();
        budget.setLimitation(newLimit);
        budgetsRepository.save(budget);

        return budget.getId();
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, consumes = "application/json")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Optional<Budget> searchResult = budgetsRepository.findById(id);
        if (searchResult.isEmpty()) {
            return ResponseEntity.ok("Nie znaleziono");
            // TODO ResponseExceptionController
        }
        return ResponseEntity.ok(searchResult);
    }

    @RequestMapping(value = "/changeLimit/{budgetId}", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody Object setMaxUsers(@PathVariable Integer budgetId, @RequestBody Budget editLimit) {
        Budget budgetEdit = budgetsRepository.findById(budgetId).orElse(null);
        if (budgetEdit == null) {
            return ResponseEntity.ok("Nie znaleziono");
            // TODO ResponseExceptionController
        }
        System.out.println(editLimit);
        System.out.println(editLimit.getLimitation());
        budgetEdit.setLimitation(editLimit.getLimitation());
        budgetsRepository.save(budgetEdit);

        return ResponseEntity.ok(budgetEdit);
    }
}
