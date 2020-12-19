package sdrprojectsmanager.sdr.teams;

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
@Entity(name = "teamsTask")
public class TeamTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(nullable = false)
    @NotNull(message = "Please provide a role id")
    private Integer roleId;

    @Column(nullable = false)
    @NotNull(message = "Please provide a task id")
    private Integer taskId;
}
