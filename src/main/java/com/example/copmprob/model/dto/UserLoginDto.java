package com.example.copmprob.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserLoginDto {
    @NotBlank(message = "Username can't be empty")
    @Size(min = 4, max = 29, message = "Username must be between 4 and 29 elements")
    private String username;

    @NotBlank(message = "Password can't be empty")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 elements")
    private String password;

    public UserLoginDto() {
    }

    public UserLoginDto(String username, String password) {
        this.username = username;
        this.password = password;
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
}
