package com.mdd.services;

import com.mdd.domain.Article;
import com.mdd.dto.ArticleRequestDTO;
import com.mdd.dto.ArticleResponseDTO;
import com.mdd.dto.ThemeResponseDTO;
import com.mdd.dto.ThemeResponseSubscribedDTO;

import java.util.List;

public interface ThemeService {

    List<ThemeResponseDTO> getThemes(String userEmail);

    List<ThemeResponseSubscribedDTO> getThemesSubscribed(String userEmail);
}
