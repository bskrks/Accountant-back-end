package com.Expert.repository;

import com.Expert.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
}
