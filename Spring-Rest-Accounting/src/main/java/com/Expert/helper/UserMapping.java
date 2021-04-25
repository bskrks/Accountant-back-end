package com.Expert.helper;

import com.Expert.dto.UserDto;
import com.Expert.entity.User;
import com.Expert.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserMapping {

    public User mapToUserSave(UserDto userDto){
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        return user;
    }

    public User mapToUserUpdate(UserDto userDto,User user){

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        return user;
    }

}
