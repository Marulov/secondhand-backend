package com.marulov.secondhand;

import com.marulov.secondhand.dto.user.UserDto;
import com.marulov.secondhand.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSupport {

    private static final Long userId = 1L;

    public static List<User> generateUsers() {
        return IntStream.range(0, 5).mapToObj(i -> User.builder()
                .id((long) i)
                .mail("mail@mail.com" + i)
                .firstName("firstName" + i)
                .lastName("lastName" + i)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .active(true)
                .build()).collect(Collectors.toList());
    }

    public static List<UserDto> generateUserDtoList(List<User> userList) {
        return userList.stream().map(user -> UserDto.builder()
                .id(user.getId())
                .mail(user.getMail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .createdDate(user.getCreatedDate())
                .updatedDate(user.getUpdatedDate())
                .active(user.getActive())
                .build()).collect(Collectors.toList());
    }

    public static User generateUser() {
        return User.builder()
                .id(userId)
                .mail("mail@mail.com")
                .firstName("firstName")
                .lastName("lastName")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .active(true)
                .build();
    }

    public static UserDto generateUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .mail(user.getMail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .createdDate(user.getCreatedDate())
                .updatedDate(user.getUpdatedDate())
                .active(true)
                .build();
    }
}