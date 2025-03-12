package com.mdd.services;


import java.util.List;

public interface SubscriptionService {


    void deleteSubscription(String userEmail, Integer themeId);

    void createSubscription(String userEmail, Integer themeId);

    List<Integer> getThemeIdsForCurrentUser(String userEmail);
}
