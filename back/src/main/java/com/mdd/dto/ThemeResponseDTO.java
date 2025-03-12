package com.mdd.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThemeResponseDTO {

    private Integer id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private boolean subscription;

}
