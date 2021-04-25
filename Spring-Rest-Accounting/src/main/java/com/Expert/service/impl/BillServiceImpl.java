package com.Expert.service.impl;

import com.Expert.Response.BillResponseModel;
import com.Expert.dto.BillDto;
import com.Expert.entity.Bill;
import com.Expert.entity.User;
import com.Expert.exception.UserNotFoundException;
import com.Expert.helper.BillMapping;
import com.Expert.repository.BillRepository;
import com.Expert.repository.UserRepository;
import com.Expert.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final BillMapping billMapping;
    private final UserRepository userRepository;
    private final static String SUCCESS="Successful !!";
    private final static String FAIL="Failed !!";

    @Value("${amount.credit-limit}")
    private Integer creditLimit;

    @Autowired
    private Environment env;

    @Override
    public BillResponseModel save(BillDto billDto) {    //John,Doe,john@doe.com,30,USB DISC,TR000
       /* Bill bill = new Bill();
        bill.setAmount(billDto.getAmount());
        bill.setProductName(billDto.getProductName());
        bill.setBillNo(billDto.getBillNo());
        bill.setConfirmed(false); */

        Boolean confirmed;
        User user = userRepository.findById(billDto.getUserId()).get();
        Integer amount = calculateAmount(billDto);
        if(creditLimit-amount>=0){
            confirmed = true;
        }
        else {
            confirmed = false;
        }

        Bill bill = billMapping.mapToBillSave(billDto, user, confirmed);
        billRepository.save(bill);

        BillResponseModel billResponseModel = new BillResponseModel();
        if(bill.getConfirmed()){
            billResponseModel.setSuccess(true);
            billResponseModel.setMessage(SUCCESS);
        }
        else{
            billResponseModel.setSuccess(false);
            billResponseModel.setMessage(FAIL);
        }

        billResponseModel.setLimit(creditLimit);
        return billResponseModel;
    }

    private Integer calculateAmount(BillDto billDto) {

        Integer amount=billRepository.getAmounts(billDto.getUserId());   // from repository
        Integer newAmount = billDto.getAmount();
        newAmount = newAmount==null?0:newAmount;
        amount = amount==null?0:amount;
        return amount+newAmount;
    }

    @Override
    @Transactional
    public void update(Long id, BillDto billDto) {
/*
        Optional<Bill> optionalBill = billRepository.findById(id);
        optionalBill.get().setAmount(billDto.getAmount());
        optionalBill.get().setProductName(billDto.getProductName());
        optionalBill.get().setBillNo(billDto.getBillNo());
        Bill updatedBill = billRepository.save(optionalBill.get());
        billDto.setId(updatedBill.getId()); */

        User user = null;
        if(billDto.getUserId()!=null)   {
            user = userRepository.findById(billDto.getUserId()).orElse(null);
        }

        Bill existingBill = billRepository.findById(id).get();
        Bill bill = billMapping.mapToBillUpdate(billDto, user, existingBill);
        billRepository.save(bill);
    }

    @Override
    public void delete(Long id) {
        billRepository.deleteById(id);
    }

    @Override
    public List<BillDto> getAll() {

        List<Bill> bills = billRepository.findAll();
        List<User> users = userRepository.findAll();
        List<BillDto> getBills = billMapping.mapToBillGet(bills);

        return getBills;
    }

    @Override
    public BillDto getById(Long id) {           // Bill değil billdto olsun

        Optional<Bill> optionalBill = billRepository.findById(id);

        BillDto readById = new BillDto();
        readById.setAmount(optionalBill.get().getAmount());
        readById.setProductName(optionalBill.get().getProductName());
        readById.setBillNo(optionalBill.get().getBillNo());
        readById.setConfirmed(optionalBill.get().getConfirmed());
        readById.setId(optionalBill.get().getId());

        if(!optionalBill.isPresent())
            throw new UserNotFoundException("Bill record is not avaliable...");

        return readById;
    }

}
