package sdrprojectsmanager.sdr.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sdrprojectsmanager.sdr.roles.Role;
import sdrprojectsmanager.sdr.roles.RolesRepository;
import sdrprojectsmanager.sdr.users.User;
import sdrprojectsmanager.sdr.users.UsersRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SdrUserDetailsService  implements UserDetailsService {

    private final UsersRepository userRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user;
        user = userRepository.findByLoginIgnoreCase(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        var role = user.getRole();
        System.out.println(role.getId());
        // TODO build authorities by roles

        if(role.getId() == 1){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            authorities.add(new SimpleGrantedAuthority("ROLE_PM"));
            authorities.add(new SimpleGrantedAuthority("ROLE_TL"));
        }
        if(role.getId() == 2) {
            authorities.add(new SimpleGrantedAuthority("ROLE_PM"));
            authorities.add(new SimpleGrantedAuthority("ROLE_TL"));
        }
        if(role.getId() == 3) {
            authorities.add(new SimpleGrantedAuthority("ROLE_TL"));
        }

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        System.out.println(authorities);
        return UserPrincipal.builder()
                .id(user.getId())
                .username(user.getLogin())
                .email(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}