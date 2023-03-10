package com.example.managingfoodreservation.Repository;

import com.example.managingfoodreservation.model.Dish;
import com.example.managingfoodreservation.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish,Integer > {
    Optional<Dish> findByDishName(String dishname);

    Optional<Dish> findByQuantity(Integer quantity);

    Optional<Dish> findByMenu(Menu menu);


    Optional<Dish> findByorderTime(Instant orderTime);
}
