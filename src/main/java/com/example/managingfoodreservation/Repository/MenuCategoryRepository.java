package com.example.managingfoodreservation.Repository;


import com.example.managingfoodreservation.model.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MenuCategoryRepository extends JpaRepository<MenuCategory,Integer> {


    @Query("select men_c from MenuCategory men_c where men_c.idMenuCategory in(select d.menuCategory from Dish d where d.status='true')")
    List<MenuCategory> getAllMenuCategory();
}
