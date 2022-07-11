package com.marulov.secondhand.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String mail;
    private String firstName;
    private String lastName;
}
