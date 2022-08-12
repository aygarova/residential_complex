package com.example.copmprob.config;

import com.example.copmprob.model.enums.RoleEnum;
import com.example.copmprob.repository.UserRepository;
import com.example.copmprob.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurator extends WebSecurityConfigurerAdapter {

    private final UserDetailsImpl userDetailsService;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SecurityConfigurator(UserDetailsImpl userDetailsService, PasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/","/contacts","/intro", "/users/login", "/users/register").permitAll()
                .antMatchers("/users/logout").hasAnyRole(RoleEnum.OWNER.name(),RoleEnum.TENANT.name(), RoleEnum.ADMIN.name())
                .antMatchers("/users/owner","/my-apartment" , "/apartment-number-enter", "/add-new-apartment-in-my-list","/give-apartment-for-rent/{apartmentNumber}", "/back-apartment/{apartmentNumber}").hasRole(RoleEnum.OWNER.name())
                .antMatchers("/all-free-apartment","/rentApartment/{apartmentNumber}" , "/leaveApartment/{apartmentNumber}").hasRole(RoleEnum.TENANT.name())
                .antMatchers("/users/admin","/users/all-owners" , "/users/all-tenants", "/categories", "/category-enter").hasRole(RoleEnum.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/users/login")
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                .defaultSuccessUrl("/")
                .failureForwardUrl("/users/login")
                .and()
                .logout().clearAuthentication(true)
                .logoutUrl("/users/logout")
                .logoutSuccessUrl("/users/login")
                .invalidateHttpSession(true)
                .deleteCookies("time")
                .deleteCookies("JSESSIONID");
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new UserDetailsImpl(userRepository);
    }

}