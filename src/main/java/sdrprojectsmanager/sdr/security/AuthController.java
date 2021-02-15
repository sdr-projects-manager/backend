package sdrprojectsmanager.sdr.security;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sdrprojectsmanager.sdr.payloads.requests.LoginRequest;
import sdrprojectsmanager.sdr.payloads.responses.JwtResponse;
import sdrprojectsmanager.sdr.users.UsersRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    final AuthenticationManager authenticationManager;
    final UsersRepository usersRepository;
    final PasswordEncoder encoder;
    final JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        // roles should be field here
        return ResponseEntity.ok(JwtResponse.builder().token(jwt).id(principal.getId()).login(principal.getUsername())
                .email(principal.getEmail()).build());
    }
}
