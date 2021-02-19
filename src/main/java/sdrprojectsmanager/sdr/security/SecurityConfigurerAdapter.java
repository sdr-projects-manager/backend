package sdrprojectsmanager.sdr.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private SdrUserDetailsService userDetailsService;

    @Autowired
    private SdrAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers("/api/auth/signin").permitAll()
                .antMatchers("/api/budgets/changeLimit/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_PM")
                .antMatchers("/api/budgets/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_PM")
                .antMatchers("/api/projects/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_PM")
                .antMatchers("/api/task/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_PM", "ROLE_TL", "ROLE_USER")
                .antMatchers("/api/task/edit/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_PM", "ROLE_TL")
                .antMatchers("/api/task/endTask").hasAnyAuthority("ROLE_ADMIN", "ROLE_PM", "ROLE_TL", "ROLE_USER")
                .antMatchers("/api/task/taskInProject/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_PM", "ROLE_TL")
                .antMatchers("/api/task/userTask/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_PM", "ROLE_TL", "ROLE_USER")
                .antMatchers("/api/teams/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_PM", "ROLE_TL")
                .antMatchers("/api/users/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_PM")
                .antMatchers("/api/users/edit/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_PM", "ROLE_TL", "ROLE_USER")
                .antMatchers("/api/raports/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_PM", "ROLE_TL")
                .anyRequest().authenticated();

        http.addFilterAfter(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
