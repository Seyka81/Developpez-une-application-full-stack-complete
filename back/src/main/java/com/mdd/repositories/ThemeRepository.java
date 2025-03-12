package com.mdd.repositories;

import com.mdd.domain.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Integer> {

    Optional<Theme> findById(Integer id);

    @Query("SELECT u.name FROM Theme u WHERE u.id = :id")
    String findNameById(Integer id);
}