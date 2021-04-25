package com.Expert.service;

import com.Expert.dto.UserDto;
import com.Expert.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(UserDto userDto);

    void update(Long id, UserDto userDto);

    void delete(Long id);

    List<UserDto> getAll();

    User getById(Long id);
}
