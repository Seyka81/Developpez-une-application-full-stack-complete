package com.mdd.services;

import java.util.ArrayList;
import java.util.Optional;
import com.mdd.model.UserDTO;

import org.springframework.http.ResponseEntity;

import com.mdd.domain.Users;
import com.mdd.model.UserRegistrationDTO;

public interface UserService {

    ResponseEntity<?> save(UserRegistrationDTO registrationDTO);

    Optional<Users> findUserById(long id);

    Users findUserByEmail(String email);

    ArrayList<Users> findAllUsers();

    Optional<Users> findUserByUsername(String username);

    UserDTO findUserByToken(String token);
}
