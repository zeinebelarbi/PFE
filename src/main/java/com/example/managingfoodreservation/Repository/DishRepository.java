package com.example.managingfoodreservation.Repository;

import com.example.managingfoodreservation.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish,Integer > {
}
