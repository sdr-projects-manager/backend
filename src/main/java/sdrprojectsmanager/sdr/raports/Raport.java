package sdrprojectsmanager.sdr.raports;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity(name = "raports")
public class Raport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer projectId;

    @Column(nullable = false)
    private Integer teamId;

    @Column(nullable = false)
    private String projectName;

    @Column(nullable = false)
    private Double costs;

    @Column(nullable = false)
    private Double profitability;
}
