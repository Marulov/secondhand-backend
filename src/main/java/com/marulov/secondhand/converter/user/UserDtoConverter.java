package com.marulov.secondhand.converter.user;

import com.marulov.secondhand.dto.user.UserDto;
import com.marulov.secondhand.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    public UserDto convert(User from) {
        return UserDto.builder()
                .id(from.getId())
                .mail(from.getMail())
                .firstName(from.getFirstName())
                .lastName(from.getLastName())
                .createdDate(from.getCreatedDate())
                .updatedDate(from.getUpdatedDate())
                .active(from.getActive())
                .build();
    }

    public List<UserDto> convert(List<User> userList) {
        return userList.stream().map(this::convert).collect(Collectors.toList());
    }
}