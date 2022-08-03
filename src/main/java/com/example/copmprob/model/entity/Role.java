//package com.example.copmprob.model.entity;
//
//import com.example.copmprob.model.enums.RoleEnum;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "roles")
//public class Role {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
//    private RoleEnum name;
//
//    @OneToMany(mappedBy = "role")
//    private List<Users> users;
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public RoleEnum getName() {
//        return name;
//    }
//
//    public void setName(RoleEnum name) {
//        this.name = name;
//    }
//
//
//}
