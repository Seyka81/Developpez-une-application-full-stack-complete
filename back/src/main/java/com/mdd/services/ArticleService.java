package com.mdd.services;

import com.mdd.domain.Article;
import com.mdd.dto.ArticleRequestDTO;
import com.mdd.dto.ArticleResponseDTO;

import java.util.List;

public interface ArticleService {

    List<ArticleResponseDTO> getArticlesForCurrentUser(String userEmail);

    void createArticle(ArticleRequestDTO articleRequest, String userEmail);

    ArticleResponseDTO getArticleById(Integer articleId);
}
