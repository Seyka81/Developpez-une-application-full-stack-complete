package com.mdd.services;

import com.mdd.domain.Article;
import com.mdd.dto.ArticleRequestDTO;
import com.mdd.dto.ArticleResponseDTO;

import java.util.List;

public interface ArticleService {

    /**
     * This method should return all articles for the current user
     * @param userEmail
     * @return List<Article>
     */
    List<ArticleResponseDTO> getArticlesForCurrentUser(String userEmail);

    /**
     * This method should create an article for the current user
     * @param articleRequest
     * @param userEmail
     */
    void createArticle(ArticleRequestDTO articleRequest, String userEmail);

    /**
     * This method should get an article by id
     * @param articleId
     */
    ArticleResponseDTO getArticleById(Integer articleId);
}
