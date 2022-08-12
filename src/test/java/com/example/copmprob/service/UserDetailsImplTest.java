package com.example.copmprob.service;

import com.example.copmprob.model.entity.Users;
import com.example.copmprob.model.enums.RoleEnum;
import com.example.copmprob.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDetailsImplTest {
    @Mock
    private UserRepository mockUserRepository;

    private UserDetailsImpl testUserDetails;

    @BeforeEach
    void setUp(){
        testUserDetails = new UserDetailsImpl(mockUserRepository);
    }

    @Test
    void testLoadUserByUsernameWithExists(){
        Users user = new Users();
        user.setUsername("Pesho1");
        user.setFirstName("Pesho");
        user.setLastName("Petrov");
        user.setPassword("123456");
        user.setPhoneNumber("0000000000");
        user.setEmail("test@gmail.com");
        user.setRole(RoleEnum.TENANT);

        when(mockUserRepository.findByUsername(user.getUsername()))
                .thenReturn(Optional.of(user));

        UserDetails userDetails = testUserDetails.loadUserByUsername(user.getUsername());

        Assertions.assertEquals(user.getUsername(),userDetails.getUsername());
    }
}
