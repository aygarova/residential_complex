package com.example.copmprob.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDto {
    private long id;
    @NotBlank(message = "First name can't be empty")
    @Size(min = 3, message = "First name must be more than 3 elements")
    private String firstName;

    @NotBlank(message = "Last name can't be empty")
    @Size(min = 3, message = "Last name must be more than 3 elements")
    private String lastName;

    @NotBlank(message = "Username can't be empty")
    @Size(min = 4, max = 29, message = "Username must be between 4 and 29 elements")
    private String username;

    @NotBlank(message = "Password can't be empty")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 elements")
    private String password;

    @NotBlank(message = "Password can't be empty")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 elements")
    private String confirmPassword;

    @NotBlank(message = "Phone number can't be empty")
    @Size(min = 10, max = 10)
    private String phoneNumber;

    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "User Type can't be empty")
    private String role;

    public UserDto() {
    }

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDto(long id, String firstName, String lastName, String username, String password, String confirmPassword, String phoneNumber, String email, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
