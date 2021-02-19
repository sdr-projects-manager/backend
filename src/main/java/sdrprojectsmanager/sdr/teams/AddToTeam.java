package sdrprojectsmanager.sdr.teams;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddToTeam {

    @Column(nullable = false)
    @NotNull(message = "Please provide a team id")
    private Integer teamId;

    @Column(nullable = false)
    @NotNull(message = "Please provide a user id")
    private Integer userId;
}
