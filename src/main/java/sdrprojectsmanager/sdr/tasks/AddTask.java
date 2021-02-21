package sdrprojectsmanager.sdr.tasks;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddTask {

    @Column(nullable = false)
    @NotNull(message = "Please provide a project id")
    private Integer projectId;

    @Column(nullable = false)
    @NotNull(message = "Please provide a user id")
    private Integer userId;

    @Column(nullable = false)
    @NotNull(message = "Please provide a name")
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @NotNull(message = "Please provide a cost")
    private Double cost;
}
