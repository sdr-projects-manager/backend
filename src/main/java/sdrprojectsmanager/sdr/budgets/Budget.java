package sdrprojectsmanager.sdr.budgets;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.ResponseEntity;
import sdrprojectsmanager.sdr.teams.Team;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity(name = "budgets")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    @NotNull(message = "Please provide a limit")
    private BigDecimal limit;

    @Column(nullable = false, columnDefinition = "default 0")
    private BigDecimal  cost;
}
