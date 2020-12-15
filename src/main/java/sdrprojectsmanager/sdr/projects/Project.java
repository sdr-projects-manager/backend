package sdrprojectsmanager.sdr.projects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    @NotNull(message = "Please provide a team id")
    private Integer teamId;

    @Column(nullable = false)
    private Integer budgetId;

    @NotNull(message = "Please provide a name")
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer State;

}
