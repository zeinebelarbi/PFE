package com.example.managingfoodreservation.Repository;


import com.example.managingfoodreservation.dto.ChefDto;
import com.example.managingfoodreservation.model.Canteenworker;
import com.example.managingfoodreservation.model.Chef;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Optional;

public interface ChefRepository extends JpaRepository< Chef,Integer> {


    Optional<Chef> findByDeliverTime(Instant deliverTime);

    Optional<Chef> findByName(String name);

    ChefDto findByCanteenworker(Canteenworker canteenworker);
}
