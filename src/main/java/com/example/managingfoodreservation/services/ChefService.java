package com.example.managingfoodreservation.services;

import com.example.managingfoodreservation.dto.CanteenworkerDto;
import com.example.managingfoodreservation.dto.ChefDto;
import com.example.managingfoodreservation.model.Canteenworker;

import java.time.Instant;
import java.util.List;

public interface ChefService {
    ChefDto save(ChefDto dto);
    ChefDto findById (Integer id );

    ChefDto findByName (String name);
    ChefDto findByCanteenworker (Canteenworker canteenworker) ;
    ChefDto findByDeliverTime(Instant DeliverTime);
    List<ChefDto> findAll();
    void delete (Integer id);
}
