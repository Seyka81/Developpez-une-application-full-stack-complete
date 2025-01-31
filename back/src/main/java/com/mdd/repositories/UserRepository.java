package com.mdd.repositories;

import java.util.ArrayList;
import org.springframework.lang.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mdd.domain.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    public Users findByEmail(String email);

    @NonNull
    public ArrayList<Users> findAll();
}
