package com.mdd.services;

import com.mdd.domain.Theme;
import com.mdd.domain.User;
import com.mdd.domain.Article;
import com.mdd.dto.ArticleRequestDTO;
import com.mdd.dto.ArticleResponseDTO;
import com.mdd.repositories.ArticleRepository;
import com.mdd.repositories.SubscriptionRepository;
import com.mdd.repositories.UserRepository;
import com.mdd.repositories.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThemeRepository themeRepository;


    @Override
    public List<ArticleResponseDTO> getArticlesForCurrentUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            throw new IllegalArgumentException("User not found with email: " + userEmail);
        }

        List<Integer> themeIds = subscriptionRepository.findThemeIdsByUserId(user.getId());

        if (themeIds.isEmpty()) {
            return Collections.emptyList();
        }


        List<Article> articles = articleRepository.findByThemeIdIn(themeIds);

        if (articles.isEmpty()) {
            return Collections.emptyList();
        }

        return articles.stream()
                .map(article -> {
                    String author = user.getName();
                    String theme = themeRepository.findNameById(article.getThemeId());
                    return new ArticleResponseDTO(article, author, theme);
                })
                .collect(Collectors.toList());
    }

    @Override
    public void createArticle(ArticleRequestDTO articleRequest, String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            throw new IllegalArgumentException("User not found with email: " + userEmail);
        }

        Theme theme = themeRepository.findById(articleRequest.getThemeId())
                .orElseThrow(
                        () -> new IllegalArgumentException("Theme not found with ID: " + articleRequest.getThemeId()));

        Article article = new Article();
        article.setUserId(user.getId());
        article.setThemeId(theme.getId());
        article.setTitle(articleRequest.getTitle());
        article.setContent(articleRequest.getContent());
        article.setCreatedAt(LocalDateTime.now());

        articleRepository.save(article);
    }

    @Override
    public ArticleResponseDTO getArticleById(Integer articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("Article not found with ID: " + articleId));

        String author = userRepository.findUsernameById(article.getUserId()).toString();
        String theme = themeRepository.findNameById(article.getThemeId());

        return new ArticleResponseDTO(article, author, theme);
    }
}
