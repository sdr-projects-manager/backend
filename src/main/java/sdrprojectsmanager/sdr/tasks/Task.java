package sdrprojectsmanager.sdr.tasks;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import sdrprojectsmanager.sdr.projects.Project;
import sdrprojectsmanager.sdr.users.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity(name = "tasks")
@NamedStoredProcedureQuery(name = "AddTask", procedureName = "AddTask", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "task_name", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "task_description", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "user_id", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "project_id", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "task_cost", type = Double.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "new_task_id", type = Integer.class), })

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    @NotNull(message = "Please provide a name")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Please provide a description")
    private String description;

    @Column(nullable = false)
    private Integer state;

    @Column(nullable = false)
    @NotNull(message = "Please provide a cost")
    private Double cost;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
