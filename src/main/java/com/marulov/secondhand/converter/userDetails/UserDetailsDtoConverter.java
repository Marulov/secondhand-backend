package com.marulov.secondhand.converter.userDetails;

import com.marulov.secondhand.dto.userDetails.UserDetailsDto;
import com.marulov.secondhand.model.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetailsDtoConverter {

    public UserDetailsDto convert(UserDetails userDetails) {
        return UserDetailsDto.builder()
                .id(userDetails.getId())
                .phoneNumber(userDetails.getPhoneNumber())
                .address(userDetails.getAddress())
                .city(userDetails.getCity())
                .country(userDetails.getCountry())
                .postCode(userDetails.getPostCode())
                .build();
    }

    public List<UserDetailsDto> convert(List<UserDetails> userDetails) {
        return userDetails.stream().map(this::convert).collect(Collectors.toList());
    }
}