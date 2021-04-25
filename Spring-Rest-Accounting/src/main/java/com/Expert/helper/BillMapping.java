package com.Expert.helper;

import com.Expert.dto.BillDto;
import com.Expert.dto.UserDto;
import com.Expert.entity.Bill;
import com.Expert.entity.User;
import com.Expert.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@DynamicInsert
@DynamicUpdate
@RequiredArgsConstructor
public class BillMapping {

    private final BillRepository billRepository;

    public Bill mapToBillSave(BillDto billDto, User user, Boolean confirmed){

        Bill bill = new Bill();
        bill.setAmount(billDto.getAmount());
        bill.setProductName(billDto.getProductName());
        bill.setBillNo(billDto.getBillNo());
        bill.setUserId(user);
        bill.setConfirmed(confirmed);

        return bill;
    }

    public Bill mapToBillUpdate(BillDto billDto, User user, Bill bill){

        bill.setAmount(billDto.getAmount());
        bill.setProductName(billDto.getProductName());
        bill.setBillNo(billDto.getBillNo());
        bill.setUserId(user);
        bill.setConfirmed(billDto.getConfirmed());
        return bill;
    }

    public List<BillDto> mapToBillGet(List<Bill> bills){

        List<BillDto> billDtos = new ArrayList<>();

        bills.forEach(bill -> {
            BillDto billDto = new BillDto();
            billDto.setId(bill.getId());
            billDto.setAmount(bill.getAmount());
            billDto.setProductName(bill.getProductName());
            billDto.setBillNo(bill.getBillNo());
            billDto.setUserId(bill.getUserId().getId());
            billDto.setFirstName(bill.getUserId().getFirstName());
            billDto.setLastName(bill.getUserId().getLastName());
            billDto.setEmail(bill.getUserId().getEmail());
            billDto.setConfirmed(bill.getConfirmed());
            billDtos.add(billDto);

        });
        return billDtos;
    }

}
