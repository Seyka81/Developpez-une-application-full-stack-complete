package com.mdd.controllers;

import com.mdd.dto.ThemeResponseDTO;
import com.mdd.dto.ThemeResponseSubscribedDTO;
import com.mdd.services.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ThemesController {


    @Autowired
    private ThemeService themeService;


    /**
     * Retrieves a list of all themes.
     *
     * @param authentication The authentication object that contains details about
     *                       the current user.
     * @return A ResponseEntity containing a list of ThemeResponseDTO.
     */
    @GetMapping("/themes")
    public ResponseEntity<List<ThemeResponseDTO>> getAllThemes( Authentication authentication) {
        String userEmail = authentication.getName();
        List<ThemeResponseDTO> themes = themeService.getThemes(userEmail);
        return ResponseEntity.ok(themes);
    }

    /**
     * Retrieves a list of themes to which the current user is subscribed.
     *
     * @param authentication The authentication object that contains details about
     *                       the current user.
     * @return A ResponseEntity containing a list of ThemeResponseSubscribedDTO.
     */
    @GetMapping("/themes/subscribed")
    public ResponseEntity<List<ThemeResponseSubscribedDTO>> getThemesSubscribed(Authentication authentication) {
        String userEmail = authentication.getName();
        List<ThemeResponseSubscribedDTO> themes = themeService.getThemesSubscribed(userEmail);
        return ResponseEntity.ok(themes);
    }

}
