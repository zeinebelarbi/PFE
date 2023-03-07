package com.example.managingfoodreservation.Repository;

import com.example.managingfoodreservation.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish,Integer > {
    Optional<Dish> findByDishName(String dishname);

    Optional<Dish> findByQuantity(Integer quantity);
}
