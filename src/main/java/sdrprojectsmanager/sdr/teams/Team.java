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
@Entity(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    @NotNull(message = "Please provide a name")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Please provide a max people in team")
    private Integer maxPeople;

}
