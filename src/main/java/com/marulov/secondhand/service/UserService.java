package com.marulov.secondhand.service;

import com.marulov.secondhand.converter.user.UserDtoConverter;
import com.marulov.secondhand.dto.user.CreateUserRequest;
import com.marulov.secondhand.dto.user.UpdateUserRequest;
import com.marulov.secondhand.dto.user.UserDto;
import com.marulov.secondhand.exception.UserNotActiveException;
import com.marulov.secondhand.exception.UserNotFoundException;
import com.marulov.secondhand.model.User;
import com.marulov.secondhand.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public List<UserDto> getAllUsers() {
        return userDtoConverter.convert(userRepository.findAll());
    }

    public UserDto getUserById(Long id) {
        User user = findUserById(id);
        return userDtoConverter.convert(user);
    }

    public UserDto createUser(CreateUserRequest createUserRequest) {
        User user = User.builder()
                .mail(createUserRequest.getMail())
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .active(false)
                .build();
        return userDtoConverter.convert(userRepository.save(user));
    }

    public UserDto updateUser(Long id, UpdateUserRequest updateUserRequest) {
        User user = findUserById(id);

        if (Boolean.FALSE.equals(user.getActive())) {
            LOGGER.warn("(updateUser) User with id {} is not active", id);
            throw new UserNotActiveException("(updateUser) User with id " + id + " not active");
        }

        user.setFirstName(updateUserRequest.getFirstName());
        user.setLastName(updateUserRequest.getLastName());
        user.setUpdatedDate(LocalDateTime.now());
        return userDtoConverter.convert(userRepository.save(user));
    }

    public void deactivateUser(Long id) {
        changeUserActivateStatus(id, false);
    }

    public void activeUser(Long id) {
        changeUserActivateStatus(id, true);
    }

    public void deleteUser(Long id) {
        if (!doesUserExists(id)) {
            LOGGER.error("(deleteUser) User with id {} not found", id);
            throw new UserNotFoundException("(deleteUser) User with id " + id + " not found");
        }
        userRepository.deleteById(id);
    }

    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("(findUserById) User with id " + id + " not found"));
    }

    private void changeUserActivateStatus(Long id, boolean active) {
        User user = findUserById(id);
        user.setActive(active);
        user.setUpdatedDate(LocalDateTime.now());
        userRepository.save(user);
    }

    private boolean doesUserExists(Long id) {
        return userRepository.existsById(id);
    }
}