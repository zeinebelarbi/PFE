package com.example.managingfoodreservation.Repository;


import com.example.managingfoodreservation.model.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MenuCategoryRepository extends JpaRepository<MenuCategory,Integer> {


    List<MenuCategory> getAllMenuCategory();
}
