package com.marulov.secondhand.repository;

import com.marulov.secondhand.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {


}
