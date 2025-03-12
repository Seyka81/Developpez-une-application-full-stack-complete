package com.mdd.controllers;

import com.mdd.dto.ResponseDTO;
import com.mdd.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    /**
     * Creates a new subscription to a theme for the current user.
     *
     * @param themeId The ID of the theme to which the user wants to subscribe.
     * @param authentication The security context of the authenticated user.
     * @return A ResponseEntity with a message indicating successful subscription.
     */
    @PostMapping("/subscribe")
    public ResponseEntity<ResponseDTO> createSubscription(@RequestBody Integer themeId, Authentication authentication) {
        String userEmail = authentication.getName();

        subscriptionService.createSubscription(userEmail, themeId);
        ResponseDTO response= new ResponseDTO("Subscribed to this theme");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Deletes a subscription to a theme for the current user.
     * This endpoint removes the subscription of the authenticated user to the specified theme ID.
     *
     * @param themeId The ID of the theme from which the user wants to unsubscribe.
     * @param authentication The security context of the authenticated user.
     * @return A ResponseEntity with HTTP 204 No Content status, indicating successful unsubscription.
     */
    @DeleteMapping("/subscribe/{themeId}")
    public ResponseEntity<ResponseDTO> deleteSubscription(@PathVariable Integer themeId, Authentication authentication) {
        String userEmail = authentication.getName();

        subscriptionService.deleteSubscription(userEmail, themeId);
        ResponseDTO response= new ResponseDTO("Unsubscribed to this theme");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}