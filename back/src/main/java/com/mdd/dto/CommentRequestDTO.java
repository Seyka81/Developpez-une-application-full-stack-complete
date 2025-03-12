package com.mdd.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentRequestDTO {

    private Integer articleId;
    @NotBlank(message = "Content cannot be blank")
    private String content;
}
