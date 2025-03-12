package com.mdd.services;

import com.mdd.domain.Theme;
import com.mdd.dto.ThemeResponseDTO;

import com.mdd.dto.ThemeResponseSubscribedDTO;
import com.mdd.repositories.SubscriptionRepository;
import com.mdd.repositories.ThemeRepository;
import com.mdd.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ThemeRepository themeRepository;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Override
    public List<ThemeResponseDTO> getThemes(String userEmail) {
        Integer userID = userRepository.findByEmail(userEmail).getId();

        List<Theme> themes = themeRepository.findAll();

        List<Integer> themesIDSubs = subscriptionRepository.findThemeIdsByUserId(userID);

        if (themes.isEmpty()) {
            return new ArrayList<>();
        }

        List<ThemeResponseDTO> response = themes.stream().map(theme -> {
            boolean isSubscribed = themesIDSubs.contains(theme.getId());

            return new ThemeResponseDTO(
                    theme.getId(),
                    theme.getName(),
                    theme.getDescription(),
                    theme.getCreatedAt(),
                    isSubscribed
            );
        }).collect(Collectors.toList());

        return response;
    }

    @Override
    public List<ThemeResponseSubscribedDTO> getThemesSubscribed(String userEmail) {
        Integer userID = userRepository.findByEmail(userEmail).getId();

        List<Theme> themes = themeRepository.findAll();

        List<Integer> themesIDSubs = subscriptionRepository.findThemeIdsByUserId(userID);

        if (themes.isEmpty()) {
            return new ArrayList<>();
        }

        List<ThemeResponseSubscribedDTO> response = themes.stream()
                .filter(theme -> themesIDSubs.contains(theme.getId()))
                .map(theme -> new ThemeResponseSubscribedDTO(
                        theme.getId(),
                        theme.getName(),
                        theme.getDescription(),
                        theme.getCreatedAt()
                ))
                .collect(Collectors.toList());

        return response;
    }

}
