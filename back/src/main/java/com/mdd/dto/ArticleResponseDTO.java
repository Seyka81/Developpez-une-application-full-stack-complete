package com.mdd.dto;

import com.mdd.domain.Article;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponseDTO {

    @NotNull
    private Integer id;

    @NotBlank
    private String author;

    @NotBlank
    private String theme;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public ArticleResponseDTO(Article article, String author, String theme) {
        this.id = article.getId();
        this.author = author;
        this.theme = theme;
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
        this.updatedAt = article.getUpdatedAt();
    }
}
