package com.marulov.secondhand.converter.user;

import com.marulov.secondhand.dto.user.UserDto;
import com.marulov.secondhand.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public UserDto convert(User from) {
        return UserDto.builder()
                .mail(from.getMail())
                .firstName(from.getFirstName())
                .lastName(from.getLastName())
                .build();
    }
}