package com.example.managingfoodreservation.Repository;

import com.example.managingfoodreservation.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuRepository  extends JpaRepository< Menu,Integer> {
    Optional<Menu> findById(Integer id);

}
