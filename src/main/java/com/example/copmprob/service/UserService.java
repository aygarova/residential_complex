package com.example.copmprob.service;

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
        RoleEnum roleEnum = switch (userDto.getRole()){
            case "OWNER" -> RoleEnum.OWNER;
            case "TENANT" -> RoleEnum.TENANT;
            default -> RoleEnum.ADMIN;
        };

        Users user = modelMapper.map(userDto, Users.class);
        user.setRole(roleEnum);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }

//    public Users findByUsernameAndPassword(String username, String password) {
//        return userRepository.findByUsernameAndPassword(username,password)
//                .orElse(null);
//    }

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

        System.out.printf("LOG IN -------------------------------------------");
        return true;
    }


    public void initFirstUser() throws Exception {
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
           Apartment apartment = apartmentRepository.findUsersApartmentByTenantId(user.getId());
           apartmentNumber.add(apartment.getApartmentNumber());
        }

        userWithApartmentDto.setApartmentsNumber(apartmentNumber);
        return userWithApartmentDto;
    }

}

