package com.web.nakshatra.repository;

import com.web.nakshatra.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
