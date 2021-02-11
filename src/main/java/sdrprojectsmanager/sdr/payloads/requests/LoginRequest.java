package sdrprojectsmanager.sdr.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {

    @NotNull(message = "Please provide a login")
    private String login;

    @NotNull(message = "Please provide a password")
    private String password;
}