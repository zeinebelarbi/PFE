package com.example.managingfoodreservation.services;

import com.example.managingfoodreservation.dto.OrderDto;
import com.example.managingfoodreservation.dto.StaffDto;
import com.example.managingfoodreservation.model.Staff;

import java.util.Date;
import java.util.List;

public interface StaffService {
    StaffDto save(StaffDto dto);
    StaffDto findByFirstName(String firstname);
    StaffDto findByLastName(String lastname);
    StaffDto findById(Integer id);


   StaffDto findByEmail(String email);
    StaffDto findByPassword(String password);
   StaffDto findByBirthDate(Date birth_date);
   StaffDto findByPhone(String phone);


    void delete (Integer id_User);

    List<StaffDto> findAll();


    List<OrderDto> order = null;
}
