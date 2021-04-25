package com.Expert.service;

import com.Expert.Response.BillResponseModel;
import com.Expert.dto.BillDto;

import java.util.List;

public interface BillService {
    BillResponseModel save(BillDto billDto);

    void update(Long id, BillDto billDto);

    void delete(Long id);

    List<BillDto> getAll();

    BillDto getById(Long id);
}
