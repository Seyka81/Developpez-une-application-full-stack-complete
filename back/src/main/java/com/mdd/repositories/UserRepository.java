package com.mdd.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mdd.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    User findUsernameById(Integer id);

    @Modifying
    @Query("UPDATE User u SET u.email = :email, u.name = :name, u.password = :password, u.updated_at = :updated_at WHERE u.id = :id")
    void updateUser(String email, String name, String password, LocalDate updated_at, Integer id);




}
