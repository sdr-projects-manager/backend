package sdrprojectsmanager.sdr.projects;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddProject {

    @Column(nullable = false)
    @NotNull(message = "Please provide a team id")
    private Integer teamId;

    @Column(nullable = false)
    @NotNull(message = "Please provide a project limit")
    private Double limitation;

    @NotNull(message = "Please provide a name")
    @Column(nullable = false)
    private String name;
}
