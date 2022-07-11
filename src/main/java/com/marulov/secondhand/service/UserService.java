package com.marulov.secondhand.service;

import com.marulov.secondhand.converter.user.UserDtoConverter;
import com.marulov.secondhand.dto.user.CreateUserRequest;
import com.marulov.secondhand.dto.user.UpdateUserRequest;
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

    public UserDto getUserById(String mail) {
        User user = findUserByMail(mail);
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

    public UserDto updateUser(String mail, UpdateUserRequest updateUserRequest) {
        User user = findUserByMail(mail);
        user.setFirstName(updateUserRequest.getFirstName());
        user.setLastName(updateUserRequest.getLastName());
        return userDtoConverter.convert(userRepository.save(user));
    }

    public void deactiveUser(String mail) {
        User user = findUserByMail(mail);
        user.setActive(false);
        userRepository.save(user);
    }

    public void deleteUser(String mail) {
        User user = findUserByMail(mail);
        userRepository.delete(user);
    }

    private User findUserByMail(String mail) {
        return userRepository.findByMail(mail)
                .orElseThrow(() -> new UserNotFoundException("User with mail " + mail + " not found"));
    }
}