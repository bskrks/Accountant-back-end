package com.Expert.repository;

import com.Expert.dto.BillDto;
import com.Expert.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {

    @Query(value = "select sum(amount) from bills where user_bill_id=?1 and confirmed=true", nativeQuery = true)
    Integer getAmounts(Long userId);

}

