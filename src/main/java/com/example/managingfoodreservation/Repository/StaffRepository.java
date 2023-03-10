package com.example.managingfoodreservation.Repository;
import com.example.managingfoodreservation.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface StaffRepository extends JpaRepository< Staff,Integer> {

    Optional<Staff> findByBirthDate(Date birthDate) ;


    Optional<Staff> findByFirstName(String firstname);

    Optional <Staff> findByLastName(String lastname);
    Optional <Staff> findByEmail(String email);

    Optional<Staff> findByPassword(String password);


    Optional<Staff> findByOrder(Staff staff);

    Optional<Staff> findByPhone(String phone);
}
