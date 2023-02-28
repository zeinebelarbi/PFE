package com.example.managingfoodreservation.Repository;

import com.example.managingfoodreservation.model.ListofOrders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListofOrdersRepository extends JpaRepository<ListofOrders,Integer > {
}
