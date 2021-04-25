package com.Expert.Response;

import com.Expert.dto.BillDto;
import com.Expert.entity.Bill;
import com.Expert.repository.BillRepository;
import lombok.*;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.Entity;

@Getter
@Setter
@Data
@ResponseBody
@AllArgsConstructor
@RequiredArgsConstructor
public class BillResponseModel {

    private Boolean success;
    private String message;
    private Integer limit;

}
