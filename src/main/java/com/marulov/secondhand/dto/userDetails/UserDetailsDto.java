package com.marulov.secondhand.dto.userDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDto {
    private Long id;
    private String phoneNumber;
    private String address;
    private String city;
    private String country;
    private String postCode;
}