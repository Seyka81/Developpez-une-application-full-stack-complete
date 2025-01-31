package com.openclassrooms.mddapi.model;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String email;
    private String password;
}

