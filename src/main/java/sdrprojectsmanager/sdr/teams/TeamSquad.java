package sdrprojectsmanager.sdr.teams;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import sdrprojectsmanager.sdr.users.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity(name = "teamsSquad")
@NamedStoredProcedureQuery(name = "AddUserToTeamSquad", procedureName = "AddUserToTeamSquad", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "user_id", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "team_id", type = Integer.class), })

public class TeamSquad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teams", referencedColumnName = "id")
    @NotNull(message = "Please provide a teamid")
    private Team teamId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users", referencedColumnName = "id")
    @NotNull(message = "Please provide a userid")
    private User userId;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
