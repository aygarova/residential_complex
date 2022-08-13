package com.example.copmprob.service;

import com.example.copmprob.exceptions.WrongActionException;
import com.example.copmprob.model.dto.UserDto;
import com.example.copmprob.model.dto.UserWithApartmentDto;
import com.example.copmprob.model.entity.Apartment;
import com.example.copmprob.model.entity.Users;
import com.example.copmprob.model.enums.ApartmentType;
import com.example.copmprob.model.enums.RoleEnum;
import com.example.copmprob.model.enums.StatusEnum;
import com.example.copmprob.repository.ApartmentRepository;
import com.example.copmprob.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class UserServiceTest {
    private UserRepository mockUserRepository;

    private  ApartmentRepository mockAartmentRepository;
    private ModelMapper modelMapper;

    private PasswordEncoder mockedPasswordEncoder;
    private UserService userService;
    private UserDetailsService userDetailsService;
    private Users testUser;
    private UserDto userDto;

    @BeforeEach
    void setUp(){
        mockUserRepository = Mockito.mock(UserRepository.class);
        mockAartmentRepository = Mockito.mock(ApartmentRepository.class);
        mockedPasswordEncoder = Mockito.mock(PasswordEncoder.class);
        modelMapper = new ModelMapper();
        userService = new UserService(mockUserRepository,
                userDetailsService, modelMapper,
                mockedPasswordEncoder, mockAartmentRepository);
        userDto = new UserDto(
                1, "Pesho", "Petrov","Pesho1","123456","123456",
                "0000000000","test@gmail.com", RoleEnum.TENANT.name()
        );
        testUser = new Users(
                "Pesho1", "Pesho", "Petrov","123456",
                "0000000000","test@gmail.com", RoleEnum.TENANT
        );

    }

    @Test
    public void testRegisterUserWithExistingEmail(){
        Users testUser = modelMapper.map(userDto,Users.class);

        when(mockUserRepository.findByEmail("test@gmail.com")).thenReturn(Optional.ofNullable(testUser));
        when(mockUserRepository.save(testUser)).thenReturn(testUser);
        Exception exception = Assertions.assertThrows(WrongActionException.class,
                () -> userService.registerUser(userDto));
        Assertions.assertEquals("User with this email already exist",exception.getMessage());
    }

    @Test
    public void testRegisterUserWithExistingUsername(){
        Users testUser = modelMapper.map(userDto,Users.class);

        when(mockUserRepository.findByUsername("Pesho1")).thenReturn(Optional.ofNullable(testUser));
        when(mockUserRepository.save(testUser)).thenReturn(testUser);
        Exception exception = Assertions.assertThrows(WrongActionException.class,
                () -> userService.registerUser(userDto));
        Assertions.assertEquals("User with this username already exist",exception.getMessage());
    }

    @Test
    public void testRegisterUser(){
        Users testUser = modelMapper.map(userDto,Users.class);

        when(mockUserRepository.save(testUser)).thenReturn(testUser);

        userService.registerUser(userDto);
    }


    @Test
    public void testChooseRoleTenant(){
        Assertions.assertEquals(RoleEnum.TENANT,userService.chooseRole(userDto.getRole()));
    }

    @Test
    public void testChooseRoleOwner(){
        userDto.setRole("OWNER");
        Assertions.assertEquals(RoleEnum.OWNER,userService.chooseRole(userDto.getRole()));
    }

    @Test
    public void testChooseRoleAdmin(){
        userDto.setRole("");
        Assertions.assertEquals(RoleEnum.ADMIN,userService.chooseRole(userDto.getRole()));
    }

    @Test
    public void testFindAllForTenant(){
        Apartment apartment = new Apartment(
                1,
                "A01F1",
                75.5,
                1,
                ApartmentType.TWO_ROOMS,
                StatusEnum.Occupied
        );
        apartment.setTenant(testUser);

        List<Users> users = new ArrayList<>();
        users.add(testUser);

        UserWithApartmentDto userWithApartmentDtos = modelMapper.map(testUser,UserWithApartmentDto.class);
        List<String> apart = new ArrayList<>();
        apart.add("A01F1");
        userWithApartmentDtos.setApartmentsNumber(apart);

        List<UserWithApartmentDto> userWithApartmentDto = new ArrayList<>();
        userWithApartmentDto.add(userWithApartmentDtos);
        when(mockUserRepository.findUsersByRole(RoleEnum.TENANT)).thenReturn(users);
        when(mockAartmentRepository.findApartmentByTenantId(testUser.getId())).thenReturn(apartment);
        when(mockAartmentRepository.findUsersApartmentByTenantId(testUser.getId())).thenReturn(apartment);
        Assertions.assertEquals(userService.findAll(RoleEnum.TENANT).get(0).getApartmentsNumber(),userWithApartmentDtos.getApartmentsNumber());
    }

    @Test
    public void testFindAllForOwner(){
        Users user = new Users(
                "Pesho1", "Pesho", "Petrov","123456",
                "0000000000","test@gmail.com", RoleEnum.OWNER
        );
        Apartment apartment = new Apartment(
                1,
                "A01F1",
                75.5,
                1,
                ApartmentType.TWO_ROOMS,
                StatusEnum.Occupied
        );
        apartment.setOwner(user);
        List<Apartment> apartments = new ArrayList<>();
        apartments.add(apartment);

        List<Users> users = new ArrayList<>();
        users.add(user);

        UserWithApartmentDto userWithApartmentDtos = modelMapper.map(user,UserWithApartmentDto.class);
        List<String> apart = new ArrayList<>();
        apart.add("A01F1");
        userWithApartmentDtos.setApartmentsNumber(apart);

        List<UserWithApartmentDto> userWithApartmentDto = new ArrayList<>();
        userWithApartmentDto.add(userWithApartmentDtos);
        when(mockUserRepository.findUsersByRole(RoleEnum.OWNER)).thenReturn(users);
        when(mockAartmentRepository.findApartmentByOwnerId(user.getId())).thenReturn(apartments);
        Assertions.assertEquals(userService.findAll(RoleEnum.OWNER).get(0).getApartmentsNumber(),userWithApartmentDtos.getApartmentsNumber());
    }

}
