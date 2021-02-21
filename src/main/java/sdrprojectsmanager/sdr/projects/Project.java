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
@NamedStoredProcedureQuery(name = "CreateProject", procedureName = "CreateProject", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "proj_name", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "team_id", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "budget_limit", type = Double.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "new_proj_id", type = Integer.class), })

@NamedStoredProcedureQuery(name = "DeleteProject", procedureName = "DeleteProject", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "input_id", type = Integer.class) })

public class Project {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "team_id", referencedColumnName = "id")
        private Team team;

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
