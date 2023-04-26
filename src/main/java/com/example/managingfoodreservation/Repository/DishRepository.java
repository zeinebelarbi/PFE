package com.example.managingfoodreservation.Repository;


import com.example.managingfoodreservation.model.Dish;
import com.example.managingfoodreservation.wrapper.DishWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface DishRepository  extends JpaRepository<Dish,Integer> {


    List<DishWrapper> getAllDish();

    @Modifying
    @Transactional
    Integer updateDishStatus(@Param("status") String status, @Param("id") int iddish);

    List<DishWrapper> getDishByMenuCategory(@Param("id") Integer iddish) ;
    DishWrapper getDishById(@Param("id") Integer iddish);
}