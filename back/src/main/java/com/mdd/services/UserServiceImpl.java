package com.mdd.services;

import com.mdd.domain.User;
import com.mdd.dto.UserDTO;
import com.mdd.dto.UserRegistrationDTO;
import com.mdd.repositories.UserRepository;
import com.mdd.security.JWTService;

import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    @Override
    public ResponseEntity<?> save(UserRegistrationDTO registrationDTO) {
        User user = new User();
        ArrayList<User> users = this.findAllUsers();
        for (User u : users) {
            if (u.getEmail().equals(registrationDTO.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already in use");
            }
        }
        user.setEmail(registrationDTO.getEmail());
        user.setName(registrationDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        user.setCreated_at(LocalDate.now());
        user.setUpdated_at(LocalDate.now());
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }

    @Override
    public Optional<User> findUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public ArrayList<User> findAllUsers() {
        return (ArrayList<User>) userRepository.findAll();
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return Optional.ofNullable(userRepository.findByEmail(username));
    }

    @Override
    public UserDTO findUserByToken(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        Claims claims = jwtService.parseToken(token);
        String username = claims.getSubject();
        Optional<User> userOptional = findUserByUsername(username);

        if (userOptional.isPresent()) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(userOptional.get().getId());
            userDTO.setName(userOptional.get().getName());
            userDTO.setEmail(userOptional.get().getEmail());
            userDTO.setCreated_at(userOptional.get().getCreated_at().toString());
            userDTO.setUpdated_at(userOptional.get().getUpdated_at().toString());

            return userDTO;
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public Optional<User> editUser(Integer id, UserRegistrationDTO userRegistrationDTO) throws Exception {

        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.updateUser(userRegistrationDTO.getEmail(), userRegistrationDTO.getUsername(),
                    passwordEncoder.encode(userRegistrationDTO.getPassword()), LocalDate.now(), id);

        } else {
            throw new Exception("User not found");
        }

        return userRepository.findById(id);
    }

}
