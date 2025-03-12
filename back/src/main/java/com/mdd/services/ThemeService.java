package com.mdd.services;

import com.mdd.domain.Article;
import com.mdd.dto.ArticleRequestDTO;
import com.mdd.dto.ArticleResponseDTO;
import com.mdd.dto.ThemeResponseDTO;
import com.mdd.dto.ThemeResponseSubscribedDTO;

import java.util.List;

public interface ThemeService {

    /**
     * This method should return all themes
     * @param userEmail
     * @return List<ThemeResponseDTO>
     */
    List<ThemeResponseDTO> getThemes(String userEmail);

    /**
     * This method should return all themes subscribed by the user
     * @param userEmail
     * @return List<ThemeResponseSubscribedDTO>
     */
    List<ThemeResponseSubscribedDTO> getThemesSubscribed(String userEmail);
}
