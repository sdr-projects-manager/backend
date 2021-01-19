package sdrprojectsmanager.sdr.users.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    public String login;
    public String password;
    public String name;
    public String lastName;
    public String email;
    public Integer role_id;
}
