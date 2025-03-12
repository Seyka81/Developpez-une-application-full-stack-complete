package com.mdd.services;

import com.mdd.domain.Article;
import com.mdd.domain.Comment;
import com.mdd.domain.User;
import com.mdd.dto.CommentRequestDTO;
import com.mdd.dto.CommentResponseDTO;
import com.mdd.repositories.ArticleRepository;
import com.mdd.repositories.CommentRepository;
import com.mdd.repositories.SubscriptionRepository;
import com.mdd.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    public List<CommentResponseDTO> getCommentsByArticleId(Integer articleId, String userEmail) {
        User user = userRepository.findByEmail(userEmail);

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new EntityNotFoundException("Article not found with ID: " + articleId));

        boolean isSubscribed = subscriptionRepository.existsByUserIdAndThemeId(user.getId(), article.getThemeId());
        if (!isSubscribed) {
            return List.of();
        }

        List<Comment> comments= commentRepository.findByArticleId(articleId);

        return comments.stream()
                .map(comment -> {
                    String author = userRepository.findUsernameById(comment.getUserId()).getName();
                    return new CommentResponseDTO(comment, author);
                })
                .collect(Collectors.toList());
    }

    public void addComment(CommentRequestDTO commentRequest, String userEmail) {
        User user = userRepository.findByEmail(userEmail);

        if (commentRequest.getArticleId() == null) {
            throw new IllegalArgumentException("Invalid articleId");
        }
        Optional<Article> article = articleRepository.findById(commentRequest.getArticleId());

        boolean isSubscribed = subscriptionRepository.existsByUserIdAndThemeId(user.getId(), article.get().getThemeId());
        if (!isSubscribed) {
            return;
        }

        Comment comment = new Comment();
        comment.setArticleId(commentRequest.getArticleId());
        comment.setUserId(user.getId());
        comment.setContent(commentRequest.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        commentRepository.save(comment);
    }
}
