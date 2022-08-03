package com.example.copmprob.repository;


import com.example.copmprob.model.entity.Users;
import com.example.copmprob.model.enums.RoleEnum;
import com.example.copmprob.service.UserDetailsImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByUsernameAndPassword(String username, String password);

    Optional<Users> findByUsername(String username);
    Users getUsersByUsername(String username);

    List<Users> findUsersByRole(RoleEnum roleEnum);
}
