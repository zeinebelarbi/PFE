package com.example.managingfoodreservation.Repository;

import com.example.managingfoodreservation.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository< Staff,Integer> {
}
