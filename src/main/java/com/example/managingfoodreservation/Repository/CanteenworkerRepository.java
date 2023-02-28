package com.example.managingfoodreservation.Repository;

import com.example.managingfoodreservation.model.Canteenworker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;


public interface CanteenworkerRepository extends JpaRepository<Canteenworker,Integer>{


    Optional<Canteenworker> findByOrderPrice(BigDecimal orderPrice);

    Optional<Canteenworker> findByOrderTime(Instant orderTime);

}
