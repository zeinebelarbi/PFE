package com.example.managingfoodreservation.services;

import com.example.managingfoodreservation.dto.CanteenworkerDto;
import com.example.managingfoodreservation.model.Canteenworker;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public interface Canteenworkerservice
{
    CanteenworkerDto save( CanteenworkerDto dto);
    CanteenworkerDto findByName(String name);

    CanteenworkerDto findById(Integer id);

    CanteenworkerDto findByOrderPrice(BigDecimal OrderPrice);
    CanteenworkerDto findByOrderTime(Instant OrderTime);
    List<CanteenworkerDto>findAll();
    void delete (Integer id);

}

