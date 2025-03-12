package com.mdd.services;

import com.mdd.domain.Subscription;
import com.mdd.domain.Theme;
import com.mdd.domain.User;
import com.mdd.repositories.SubscriptionRepository;
import com.mdd.repositories.ThemeRepository;
import com.mdd.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Transactional
@Service
public class SubscriptionServiceImpl implements SubscriptionService{

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThemeRepository themeRepository;



    public void createSubscription(String userEmail, Integer themeId) {
        User user = userRepository.findByEmail(userEmail);

        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new EntityNotFoundException("Theme not found with ID: " + themeId));

        Subscription subscription = new Subscription();
        subscription.setUserId(user.getId());
        subscription.setThemeId(theme.getId());
        subscription.setCreatedAt(LocalDateTime.now());

        subscriptionRepository.save(subscription);
    }

    public void deleteSubscription(String userEmail, Integer themeId) {
        User user = userRepository.findByEmail(userEmail);

        Subscription subscription = subscriptionRepository.findByUserIdAndThemeId(user.getId(), themeId)
                .orElseThrow(() -> new EntityNotFoundException("Subscription not found"));

        subscriptionRepository.delete(subscription);
    }


}
