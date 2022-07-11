package com.marulov.secondhand.dto.user;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String mail;
    private String firstName;
    private String lastName;
}
