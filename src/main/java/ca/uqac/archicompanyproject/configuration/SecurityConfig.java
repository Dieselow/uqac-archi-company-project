package ca.uqac.archicompanyproject.configuration;

import ca.uqac.archicompanyproject.domain.authentication.Roles;
import ca.uqac.archicompanyproject.security.JwtTokenFilter;
import ca.uqac.archicompanyproject.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private TokenProvider tokenProvider;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/welcome").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/patients/auth/**").permitAll()
                .antMatchers("/secretaries/auth/**").permitAll()
                .antMatchers("/caregivers/auth/**").permitAll()
                .antMatchers("/patients/**").hasRole(Roles.PATIENT.toString())
                .antMatchers("/caregivers/").hasRole(Roles.CAREGIVER.toString())
                .antMatchers("/secretaries").hasRole(Roles.SECRETARY.toString())
                //.antMatchers("/**").hasRole(Roles.ADMIN.toString())
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(new JwtTokenFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}