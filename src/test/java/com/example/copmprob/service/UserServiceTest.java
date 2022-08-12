//package com.example.copmprob.service;
//
//import com.example.copmprob.exceptions.WrongActionException;
//import com.example.copmprob.model.entity.Apartment;
//import com.example.copmprob.model.entity.Users;
//import com.example.copmprob.model.enums.RoleEnum;
//import com.example.copmprob.repository.ApartmentRepository;
//import com.example.copmprob.repository.UserRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.modelmapper.ModelMapper;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.Mockito.when;
//
//public class UserServiceTest {
//    private UserRepository mockUserRepository;
//
//    private  ApartmentRepository mockAartmentRepository;
//    private ModelMapper modelMapper;
//
//    private PasswordEncoder mockedPasswordEncoder;
//    private UserService userService;
//    private UserDetailsService userDetailsService;
//    private Users testUser;
//    private UserDto userDto;
//
//    @BeforeEach
//    void setUp(){
//        mockUserRepository = Mockito.mock(UserRepository.class);
//        mockAartmentRepository = Mockito.mock(ApartmentRepository.class);
//        mockedPasswordEncoder = Mockito.mock(PasswordEncoder.class);
//        modelMapper = new ModelMapper();
//        userService = new UserService(mockUserRepository,
//                userDetailsService, modelMapper,
//                mockedPasswordEncoder, mockAartmentRepository);
//        userDto = new UserDto(
//                1, "Pesho", "Petrov","Pesho1","123456","123456",
//                "0000000000","test@gmail.com", RoleEnum.TENANT.name()
//        );
//        testUser = new Users(
//                "Pesho1", "Pesho", "Petrov","123456",
//                "0000000000","test@gmail.com", RoleEnum.TENANT
//        );
//
//    }
//
//    @Test
//    public void testRegisterUserWithExistingEmail(){
//        Users testUser = modelMapper.map(userDto,Users.class);
//
//        when(mockUserRepository.findByEmail("test@gmail.com")).thenReturn(Optional.ofNullable(testUser));
//        when(mockUserRepository.save(testUser)).thenReturn(testUser);
//        Exception exception = Assertions.assertThrows(WrongActionException.class,
//                () -> userService.registerUser(userDto));
//        Assertions.assertEquals("User with this email already exist",exception.getMessage());
//    }
//
//    @Test
//    public void testRegisterUserWithExistingUsername(){
//        Users testUser = modelMapper.map(userDto,Users.class);
//
//        when(mockUserRepository.findByUsername("Pesho1")).thenReturn(Optional.ofNullable(testUser));
//        when(mockUserRepository.save(testUser)).thenReturn(testUser);
//        Exception exception = Assertions.assertThrows(WrongActionException.class,
//                () -> userService.registerUser(userDto));
//        Assertions.assertEquals("User with this username already exist",exception.getMessage());
//    }
//
//    @Test
//    public void testRegisterUser(){
//        Users testUser = modelMapper.map(userDto,Users.class);
//
//        when(mockUserRepository.save(testUser)).thenReturn(testUser);
//
//        userService.registerUser(userDto);
//    }
//
//
//    @Test
//    public void testChooseRoleTenant(){
//        Assertions.assertEquals(RoleEnum.TENANT,userService.chooseRole(userDto.getRole()));
//    }
//
//    @Test
//    public void testChooseRoleOwner(){
//        userDto.setRole("OWNER");
//        Assertions.assertEquals(RoleEnum.OWNER,userService.chooseRole(userDto.getRole()));
//    }
//
//    @Test
//    public void testChooseRoleAdmin(){
//        userDto.setRole("");
//        Assertions.assertEquals(RoleEnum.ADMIN,userService.chooseRole(userDto.getRole()));
//    }
//
//    @Test
//    public void testFindAll(){
//        List<Users> users = new ArrayList<>();
//        Apartment apartment = new Apartment();
//        apartment.setApartmentNumber("A01F1");
//        apartment.setOwner(testUser);
//        List<Apartment> apartments = new ArrayList<>();
//        apartments.add(apartment);
//        testUser.setRole(RoleEnum.OWNER);
//        users.add(testUser);
//        UserWithApartmentDto userWithApartmentDtos = modelMapper.map(testUser,UserWithApartmentDto.class);
//        List<String> apart = new ArrayList<>();
//        apart.add("A01F1");
//        userWithApartmentDtos.setApartmentsNumber(apart);
//        when(mockUserRepository.findUsersByRole(RoleEnum.OWNER)).thenReturn(users);
//
//        when(mockAartmentRepository.findApartmentByOwnerId(testUser.getId())).thenReturn(apartments);
//
//        Assertions.assertEquals(userService.findAll(RoleEnum.OWNER),userWithApartmentDtos);
//    }
//
//}
