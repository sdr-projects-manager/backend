package sdrprojectsmanager.sdr.users.dtos;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    @Column(nullable = false)
    @NotNull(message = "Please provide a login")
    public String login;

    @Column(nullable = false)
    @NotNull(message = "Please provide a password")
    public String password;

    @NotNull(message = "Please provide a name")
    @Column(nullable = false)
    public String name;

    @NotNull(message = "Please provide a last name")
    @Column(nullable = false)
    public String lastName;

    @NotNull(message = "Please provide a email")
    @Column(nullable = false)
    public String email;

    @NotNull(message = "Please provide a role id")
    @Column(nullable = false)
    public Integer role_id;
}
