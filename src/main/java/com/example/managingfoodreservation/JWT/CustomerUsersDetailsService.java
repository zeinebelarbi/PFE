package com.example.managingfoodreservation.JWT;

import com.example.managingfoodreservation.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
@Slf4j
@Service
public class CustomerUsersDetailsService implements UserDetailsService {
    @Autowired

    UserRepository userRepository;

    private com.example.managingfoodreservation.model.User userDetail;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Inside loadUserByUsername{}",username);
      userDetail=userRepository.findByEmailId(username);
      if (!Objects.isNull(userDetail))
          return new User(userDetail.getEmail(),userDetail.getPassword(),new ArrayList<>());
else
    throw new UsernameNotFoundException("User not found.");
    }
    public com.example.managingfoodreservation.model.User getUserDetail(){

        return userDetail;
    }
}
