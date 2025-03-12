package com.mdd.dto;

import com.mdd.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDTO {
    private Integer id;
    private Integer articleId;
    private String author;
    private String content;
    private LocalDateTime createdAt;

    public CommentResponseDTO(Comment comment, String author) {
        this.id = comment.getId();
        this.articleId = comment.getArticleId();
        this.author = author;
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
    }
}