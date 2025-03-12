package com.mdd.dto;

import lombok.Data;

@Data
public class UserDTO {

    protected Integer id;

    private String name;

    private String email;

    private String password;

    private String created_at;

    private String updated_at;

}
