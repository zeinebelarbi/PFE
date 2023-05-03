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
<<<<<<< HEAD
 @Query("SELECT new com.example.managingfoodreservation.wrapper.UserWrapper(u.id, u.username, u.email, u.password, u.role, u.status) FROM User u")
=======
 @Query("SELECT u FROM User uELECT new com.example.managingfoodreservation.wrapper.UserWrapper(u.id, u.username, u.email, u.password, u.role, u.status) FROM User u")
>>>>>>> 471fcb86fb2f9d54799c7c7fceec1e68078c336d
 List<UserWrapper> getAllUser();

 @Query("SELECT u.email FROM User u WHERE u.role = 'admin'")
 List<String> getAllAdmin();

@Transactional
@Modifying
 Integer updateStatus(@Param("status" )String status,@Param("id" )Integer id );
User findByEmail(String email);
}
