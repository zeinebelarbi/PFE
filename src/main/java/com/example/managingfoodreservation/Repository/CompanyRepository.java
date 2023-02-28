package com.example.managingfoodreservation.Repository;

import com.example.managingfoodreservation.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
}
