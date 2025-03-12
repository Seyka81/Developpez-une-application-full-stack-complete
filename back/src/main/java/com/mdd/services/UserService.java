package com.mdd.services;

import java.util.ArrayList;
import java.util.Optional;
import com.mdd.dto.UserDTO;

import org.springframework.http.ResponseEntity;

import com.mdd.domain.User;
import com.mdd.dto.UserRegistrationDTO;

public interface UserService {

    ResponseEntity<?> save(UserRegistrationDTO registrationDTO);

    Optional<User> findUserById(Integer id);

    User findUserByEmail(String email);

    ArrayList<User> findAllUsers();

    Optional<User> findUserByUsername(String username);

    UserDTO findUserByToken(String token);

    Optional<User> editUser(Integer id, UserRegistrationDTO userRegistrationDTO) throws Exception;
}
