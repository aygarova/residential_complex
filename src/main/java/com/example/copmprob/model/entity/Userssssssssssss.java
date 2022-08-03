//package com.example.copmprob.model.entity;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//@Entity
//@Table(name = "users")
//public class Users {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(name = "username",nullable = false, unique = true)
//    private String username;
//
//    @Column(name = "first_name", nullable = false)
//    private String firstName;
//
//    @Column(name = "last_name", nullable = false)
//    private String lastName;
//
//    @Column(name = "password",nullable = false)
//    private String password;
//
//    @Column(name = "phone_number", nullable = false)
//    private String phoneNumber;
//
//    @Column(name = "email", nullable = false, unique = true)
//    private String email;
//
//    @Enumerated(EnumType.STRING)
//    @ManyToMany(fetch = FetchType.EAGER)
//    private List<Role> roles = new ArrayList<>();
//
//
//
//    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
//    private Set<Apartment> apartments;
//
//    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
//    private Set<News> news;
//
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//
//    public Set<Apartment> getApartments() {
//        return apartments;
//    }
//
//    public void setApartments(Set<Apartment> apartments) {
//        this.apartments = apartments;
//    }
//
//    public List<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<Role> roles) {
//        this.roles = roles;
//    }
//
//    public Set<News> getNews() {
//        return news;
//    }
//
//    public void setNews(Set<News> news) {
//        this.news = news;
//    }
//}
