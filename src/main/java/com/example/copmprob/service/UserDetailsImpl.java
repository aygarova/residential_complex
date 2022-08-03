package com.example.copmprob.service;


import com.example.copmprob.model.entity.Users;
import com.example.copmprob.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class UserDetailsImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return userRepository.
                findByUsername(username).
                map(this::map).
                orElse(null);
                //orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found!"));
    }

    private UserDetails map(Users userEntity) {
        return
                User.builder().
                        username(userEntity.getUsername()).
                        password(userEntity.getPassword()).
                        authorities(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole().name())).
                        build();
    }
}
