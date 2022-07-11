package com.marulov.secondhand.service;

import com.marulov.secondhand.converter.user.UserDtoConverter;
import com.marulov.secondhand.dto.user.CreateUserRequest;
import com.marulov.secondhand.dto.user.UserDto;
import com.marulov.secondhand.exception.UserNotFoundException;
import com.marulov.secondhand.model.User;
import com.marulov.secondhand.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userDtoConverter::convert).collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        return userDtoConverter.convert(user);
    }

    public UserDto createUser(CreateUserRequest createUserRequest) {
        User user = User.builder()
                .mail(createUserRequest.getMail())
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .build();
        return userDtoConverter.convert(userRepository.save(user));
    }
}