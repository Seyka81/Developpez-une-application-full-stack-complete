package com.mdd.repositories;

import com.mdd.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {


    @Query("SELECT s.themeId FROM Subscription s WHERE s.userId = :userId")
    List<Integer> findThemeIdsByUserId(Integer userId);

    Optional<Subscription> findByUserIdAndThemeId(Integer userId, Integer themeId);

    boolean existsByUserIdAndThemeId(Integer userId, Integer themeId);
}