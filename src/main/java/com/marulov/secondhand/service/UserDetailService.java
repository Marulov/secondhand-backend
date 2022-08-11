package com.marulov.secondhand.service;

import com.marulov.secondhand.converter.userDetails.UserDetailsDtoConverter;
import com.marulov.secondhand.dto.userDetails.CreateUserDetailsRequest;
import com.marulov.secondhand.dto.userDetails.UpdateUserDetailsRequest;
import com.marulov.secondhand.dto.userDetails.UserDetailsDto;
import com.marulov.secondhand.exception.UserNotFoundException;
import com.marulov.secondhand.model.User;
import com.marulov.secondhand.model.UserDetails;
import com.marulov.secondhand.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailService.class);

    private final UserDetailsRepository userDetailsRepository;
    private final UserDetailsDtoConverter userDetailsDtoConverter;
    private final UserService userService;

    public List<UserDetailsDto> getAll() {
        return userDetailsDtoConverter.convert(userDetailsRepository.findAll());
    }

    public UserDetailsDto getUserDetailsById(Long id) {
        return userDetailsDtoConverter.convert(userDetailsRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("UserDetails with id " + id + " not found")));
    }

    public UserDetailsDto createUserDetails(CreateUserDetailsRequest createUserDetailsRequest) {
        User user = userService.findUserById(createUserDetailsRequest.getUserId());

        UserDetails userDetails = UserDetails.builder()
                .phoneNumber(createUserDetailsRequest.getPhoneNumber())
                .address(createUserDetailsRequest.getAddress())
                .city(createUserDetailsRequest.getCity())
                .country(createUserDetailsRequest.getCountry())
                .postCode(createUserDetailsRequest.getPostCode())
                .user(user)
                .build();
        return userDetailsDtoConverter.convert(userDetailsRepository.save(userDetails));
    }

    public UserDetailsDto updateUserDetails(Long id, UpdateUserDetailsRequest updateUserDetailsRequest) {
        UserDetails userDetails = findUserDetailsById(id);

        userDetails.setPhoneNumber(updateUserDetailsRequest.getPhoneNumber());
        userDetails.setAddress(updateUserDetailsRequest.getAddress());
        userDetails.setCity(updateUserDetailsRequest.getCity());
        userDetails.setCountry(updateUserDetailsRequest.getCountry());
        userDetails.setPostCode(updateUserDetailsRequest.getPostCode());
        return userDetailsDtoConverter.convert(userDetailsRepository.save(userDetails));
    }

    public void deleteUserDetails(Long id) {
        UserDetails userDetails = findUserDetailsById(id);
        userDetailsRepository.delete(userDetails);
    }

    private UserDetails findUserDetailsById(Long id) {
        return userDetailsRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("UserDetails with Id " + id + " not found"));
    }
}