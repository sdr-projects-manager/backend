package sdrprojectsmanager.sdr.tasks;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    @NotNull(message = "Please provide a role id")
    private Integer projectId;

    @Column(nullable = false)
    @NotNull(message = "Please provide a role id")
    private Integer userId;

    @Column(nullable = false)
    @NotNull(message = "Please provide a name")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Please provide a name")
    private String description;

    @Column(nullable = false)
    @NotNull(message = "Please provide a name")
    private Integer state;

    @Column(nullable = false)
    @NotNull(message = "Please provide a name")
    private BigDecimal cost;

}
