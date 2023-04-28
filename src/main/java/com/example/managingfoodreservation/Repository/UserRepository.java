package com.example.managingfoodreservation.Repository;
import com.example.managingfoodreservation.model.User;
import com.example.managingfoodreservation.wrapper.UserWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


public interface UserRepository extends JpaRepository<User,Integer> {


 User findByEmailId(@Param("email") String email);
 @Query("SELECT u FROM User u")
 List<UserWrapper> getAllUser();

 @Query("SELECT u.email FROM User u WHERE u.role = 'admin'")
 List<String> getAllAdmin();

@Transactional
@Modifying
 Integer updateStatus(@Param("status" )String status,@Param("idUser" )Integer idUser );
User findByEmail(String email);
}
