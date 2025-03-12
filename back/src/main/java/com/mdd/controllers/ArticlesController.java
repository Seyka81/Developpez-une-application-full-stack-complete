package com.mdd.controllers;

import com.mdd.dto.ArticleRequestDTO;
import com.mdd.dto.ArticleResponseDTO;
import com.mdd.dto.ResponseDTO;
import com.mdd.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.mdd.services.ArticleService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ArticlesController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleService articleService;

    /**
     * Retrieves a list of article responses for the current authenticated user.
     *
     * @param authentication The authentication object that contains details about
     *                       the current user.
     * @return A ResponseEntity containing a list of ArticleResponse.
     */
    @GetMapping("/articles")
    public ResponseEntity<List<ArticleResponseDTO>> getArticlesForCurrentUser(Authentication authentication) {
        String userEmail = authentication.getName();
        List<ArticleResponseDTO> articleResponses = articleService.getArticlesForCurrentUser(userEmail);
        return ResponseEntity.ok(articleResponses);
    }

    /**
     * Creates a new article based on the provided article request and the current
     * authenticated user.
     *
     * @param articleRequest The request body containing article data.
     * @param authentication The authentication object that contains details about
     *                       the current user.
     * @return A ResponseEntity with a message response indicating the status.
     */
    
    @PostMapping("/article/create")
    public ResponseEntity<ResponseDTO> createArticle(@Valid @RequestBody ArticleRequestDTO articleRequest,
            Authentication authentication) {
        String userEmail = authentication.getName();
        articleService.createArticle(articleRequest, userEmail);
        ResponseDTO response= new ResponseDTO("Article has been created.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Retrieves an article response by its ID.
     *
     * @param articleId The ID of the article to retrieve.
     * @return A ResponseEntity containing the ArticleResponse.
     */
    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleResponseDTO> getArticleById(@PathVariable Integer articleId) {
        ArticleResponseDTO articleResponse = articleService.getArticleById(articleId);
        return ResponseEntity.ok(articleResponse);
    }
}
