package com.example.managingfoodreservation.services;

import com.example.managingfoodreservation.dto.CanteenworkerDto;

import java.time.Instant;
import java.util.List;

public interface CanteenworkerService {
    CanteenworkerDto save( CanteenworkerDto dto);
    CanteenworkerDto findByName(String name);

    CanteenworkerDto findById(Integer id);

    CanteenworkerDto findByOrderPrice(Double OrderPrice);
    CanteenworkerDto findByOrderTime(Instant OrderTime);
    List<CanteenworkerDto> findAll();
    void delete (Integer id);
}
