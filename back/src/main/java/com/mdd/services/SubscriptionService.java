package com.mdd.services;


import java.util.List;

public interface SubscriptionService {

    /**
     * This method should return all subscriptions for the current user
     * @param userEmail
     * @return List<Subscription>
     */
    void deleteSubscription(String userEmail, Integer themeId);

    /**
     * This method should create a subscription for the current user
     * @param userEmail
     * @param themeId
     */
    void createSubscription(String userEmail, Integer themeId);


}
