package com.mdd.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ArticleRequestDTO {

    @NotNull
    private Integer themeId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
