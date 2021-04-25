package com.Expert.dto;

import com.Expert.entity.Bill;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Data
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

}