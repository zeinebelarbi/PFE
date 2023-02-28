package com.example.managingfoodreservation.Repository;

import com.example.managingfoodreservation.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository  extends JpaRepository< Menu,Integer> {
}
