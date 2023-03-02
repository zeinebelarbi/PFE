package com.example.managingfoodreservation.Repository;

import com.example.managingfoodreservation.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository< Order,Integer> {
 Optional<Order> findById(Integer id);
 Optional <Order> findByDate(Instant date);
 Optional <Order> findByName(String name);
}
