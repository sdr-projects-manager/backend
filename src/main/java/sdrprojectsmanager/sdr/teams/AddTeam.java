package sdrprojectsmanager.sdr.teams;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddTeam {

  @Column(nullable = false)
  @NotNull(message = "Please provide a name")
  private String name;

  @Column(nullable = false)
  @NotNull(message = "Please provide a max people in team")
  private Integer maxPeople;

  @Column(nullable = false)
  @NotNull(message = "Please provide users")
  private List<Integer> users;
}
