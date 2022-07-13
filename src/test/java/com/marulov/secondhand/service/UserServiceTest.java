package com.marulov.secondhand.service;

import com.marulov.secondhand.TestSupport;
import com.marulov.secondhand.converter.user.UserDtoConverter;
import com.marulov.secondhand.dto.user.UserDto;
import com.marulov.secondhand.exception.UserNotFoundException;
import com.marulov.secondhand.model.User;
import com.marulov.secondhand.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.TestInstance.*;
import static org.mockito.Mockito.*;

@TestInstance(Lifecycle.PER_CLASS)
class UserServiceTest extends TestSupport {

    UserRepository userRepository;
    UserDtoConverter userDtoConverter;

    UserService userService;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userDtoConverter = mock(UserDtoConverter.class);

        userService = new UserService(userRepository, userDtoConverter);
    }

    @Test
    void testGetAllUsers_itShouldReturnUserDtoList() {
        List<User> userList = generateUsers();
        List<UserDto> userDtoList = generateUserDtoList(userList);

        when(userRepository.findAll()).thenReturn(userList);
        when(userDtoConverter.convert(userList)).thenReturn(userDtoList);

        List<UserDto> result = userService.getAllUsers();

        assertEquals(userDtoList, result);
        verify(userRepository).findAll();
        verify(userDtoConverter).convert(userList);
    }

    @Test
    void testGetUserById_whenUserIdExists_itShouldReturnUserDto() {
        Long userId = 1L;
        User user = generateUser();
        UserDto userDto = generateUserDto(user);


        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userDtoConverter.convert(user)).thenReturn(userDto);

        UserDto result = userService.getUserById(userId);

        assertEquals(userDto, result);
        verify(userRepository).findById(userId);
        verify(userDtoConverter).convert(user);
    }

    @Test
    void testGetUserById_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));

        verify(userRepository).findById(userId);
        verifyNoInteractions(userDtoConverter);
    }
}