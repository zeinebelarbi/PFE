package com.example.managingfoodreservation.Repository;

import com.example.managingfoodreservation.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill,Integer> {
    List<Bill> getAllBills();

    List<Bill> getAllBillByUserName(@Param("username") String username);
}
