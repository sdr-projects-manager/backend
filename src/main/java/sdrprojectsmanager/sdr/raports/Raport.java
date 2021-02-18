package sdrprojectsmanager.sdr.raports;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity(name = "raports")
@NamedStoredProcedureQuery(name = "CreateRaport",
        procedureName = "CreateRaport", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "project_id", type = Integer.class)})

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

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
