package sdrprojectsmanager.sdr.users;

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
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    @NotNull(message = "Please provide a login")
    private String login;

    @Column(nullable = false)
    @NotNull(message = "Please provide a password")
    private String password;

    @NotNull(message = "Please provide a name")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Please provide a lastName")
    @Column(nullable = false)
    private String lastName;

    @NotNull(message = "Please provide a email")
    @Column(unique = true, nullable = false)
    private String email;
}