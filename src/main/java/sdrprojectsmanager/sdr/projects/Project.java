package sdrprojectsmanager.sdr.projects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import sdrprojectsmanager.sdr.budgets.Budget;
import sdrprojectsmanager.sdr.teams.Team;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "Please provide a team id")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    @NotNull(message = "Please provide a budget id")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "budget", referencedColumnName = "id")
    private Budget budget;

    @NotNull(message = "Please provide a name")
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer State;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
