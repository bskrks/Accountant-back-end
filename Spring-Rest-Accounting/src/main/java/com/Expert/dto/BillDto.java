package com.Expert.dto;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@Data
@DynamicUpdate
public class BillDto {

    private Long id;

    private Integer amount ;

    private Boolean confirmed;

    private String productName;

    private String billNo;

    private Long userId;

    private String firstName;

    private String lastName;

    private String email;

}
