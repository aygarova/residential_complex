package com.example.copmprob.service;

import com.example.copmprob.exceptions.ExceptionMessages;
import com.example.copmprob.exceptions.WrongActionException;
import com.example.copmprob.model.dto.UserDto;
import com.example.copmprob.model.dto.UserLoginDto;
import com.example.copmprob.model.dto.UserWithApartmentDto;
import com.example.copmprob.model.entity.Apartment;
import com.example.copmprob.model.entity.Users;
import com.example.copmprob.model.enums.RoleEnum;
import com.example.copmprob.repository.ApartmentRepository;
import com.example.copmprob.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final UserDetailsService userDetailsService;
    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final ApartmentRepository apartmentRepository;

    public UserService(UserRepository userRepository, UserDetailsService userDetailsService, ModelMapper modelMapper, PasswordEncoder passwordEncoder, ApartmentRepository apartmentRepository) {
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.apartmentRepository = apartmentRepository;
    }

    public void registerUser(UserDto userDto) {
        RoleEnum roleEnum = chooseRole(userDto.getRole());

        Users user = modelMapper.map(userDto, Users.class);
        Users userWithThisUsername = userRepository.findByUsername(user.getUsername()).orElse(null);
        Users usersWithThisEmail = userRepository.findByEmail(user.getEmail()).orElse(null);
        if (!Objects.isNull(userWithThisUsername)){
            throw new WrongActionException(ExceptionMessages.USERNAME_ALREADY_EXIST_EXCEPTIONS);
        }
        if (!Objects.isNull(usersWithThisEmail)){
            throw new WrongActionException(ExceptionMessages.EMAIL_ALREADY_EXIST_EXCEPTIONS);
        }

        user.setRole(roleEnum);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }

    protected RoleEnum chooseRole(String role) {
        switch (role) {
            case "OWNER" -> {
                return RoleEnum.OWNER;
            }
            case "TENANT" -> {
                return RoleEnum.TENANT;
            }
            default -> {
                return RoleEnum.ADMIN;
            }
        }
    }

    public boolean loginUser(UserLoginDto userDto) {
        Users newUser = new Users();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(userDto.getPassword());


        UserDetails userDetails =
                userDetailsService.loadUserByUsername(newUser.getUsername());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


        if (userDetails == null || (!encoder.matches(newUser.getPassword(),userDetails.getPassword()))){
            return false;
        }

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.
                getContext().
                setAuthentication(auth);

        return true;
    }


    public void initFirstUser() {
        if (this.userRepository.count() == 0) {

            Users user = new Users();
            user.setUsername("admin");
            user.setFirstName("Admin");
            user.setLastName("Adminov");
            user.setPassword(this.passwordEncoder.encode("123456"));
            user.setPhoneNumber("0881111111");
            user.setEmail("admin@gmail.com");
            user.setRole(RoleEnum.ADMIN);
            this.userRepository.save(user);
        }
    }

    public List<UserWithApartmentDto> findAll(RoleEnum roleEnum) {
        List<Users> users = new ArrayList<>();
        if (roleEnum.equals(RoleEnum.OWNER)){
            users = userRepository.findUsersByRole(RoleEnum.OWNER);
        } else if (roleEnum.equals(RoleEnum.TENANT)) {
            users = userRepository.findUsersByRole(RoleEnum.TENANT);
        }
        List<UserWithApartmentDto> userWithApartmentDtos = new ArrayList<>();

        for (Users user: users){
            userWithApartmentDtos.add(convertUserToDto(user));
        }

        return userWithApartmentDtos;
    }

    private UserWithApartmentDto convertUserToDto(Users user) {
        UserWithApartmentDto userWithApartmentDto = modelMapper.map(user, UserWithApartmentDto.class);
        List<String> apartmentNumber = new ArrayList<>();

        if (user.getRole().equals(RoleEnum.OWNER)){
            List<Apartment> apartments = apartmentRepository.findApartmentByOwnerId(user.getId());
            for (Apartment a:apartments) {
                apartmentNumber.add(a.getApartmentNumber());
            }
        } else if (user.getRole().equals(RoleEnum.TENANT)) {
            if (user.getRentApartments() != null){
                Apartment apartment = apartmentRepository.findUsersApartmentByTenantId(user.getId());
                apartmentNumber.add(apartment.getApartmentNumber());
            }
        }

        userWithApartmentDto.setApartmentsNumber(apartmentNumber);
        return userWithApartmentDto;
    }

}
