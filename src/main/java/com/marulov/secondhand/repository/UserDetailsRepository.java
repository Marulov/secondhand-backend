package com.marulov.secondhand.repository;

import com.marulov.secondhand.dto.userDetails.UserDetailsDto;
import com.marulov.secondhand.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    Optional<List<UserDetails>> findUserDetailsByUser_Id(Long userId);
}
