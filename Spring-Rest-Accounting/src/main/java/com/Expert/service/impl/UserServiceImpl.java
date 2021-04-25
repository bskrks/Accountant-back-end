package com.Expert.service.impl;

import com.Expert.dto.UserDto;
import com.Expert.entity.User;
import com.Expert.exception.UserNotFoundException;
import com.Expert.helper.UserMapping;
import com.Expert.repository.UserRepository;
import com.Expert.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapping userMapping;

    @Override
    @Transactional
    public void save(UserDto userDto) {

        User user = userMapping.mapToUserSave(userDto);
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long id, UserDto userDto){

        User existingUser = userRepository.findById(id).get();
        User user = userMapping.mapToUserUpdate(userDto, existingUser);
        userRepository.save(user);
/*
        optionalUser.get().setFirstName(userDto.getFirstName());
        optionalUser.get().setLastName(userDto.getLastName());
        optionalUser.get().setEmail(userDto.getEmail());
        User updatedUser = userRepository.save(optionalUser.get());
        userDto.setId(updatedUser.getId());

        return userDto;  */
    }

    @Override
    public List<UserDto> getAll() {

        List<User> user_list = userRepository.findAll();       // We get the users from userRepository by 'findAll'
        List<UserDto> userDtos = new ArrayList<>();

        user_list.forEach(it -> {
            UserDto userDto = new UserDto();
            userDto.setId(it.getId());
            userDto.setFirstName(it.getFirstName());
            userDto.setLastName(it.getLastName());
            userDto.setEmail(it.getEmail());
            userDtos.add(userDto);
        });
        return userDtos;
    }

   @Override
   public User getById(Long id) {

       Optional<User> optionalUser = userRepository.findById(id);

       if(!optionalUser.isPresent())
           throw new UserNotFoundException("User record is not avaliable...");

       return optionalUser.get();
    }
}
