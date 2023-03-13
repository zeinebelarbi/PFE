package com.example.managingfoodreservation.controller;
import com.example.managingfoodreservation.dto.StaffDto;
import com.example.managingfoodreservation.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.example.managingfoodreservation.utils.Constants.APP_ROOT;

@RestController
@RequestMapping("/staff")
public abstract  class StaffController {

    private StaffService staffservice;
@Autowired
    public StaffController(StaffService staffservice){

    this.staffservice=staffservice;
    }
    @PostMapping(value =APP_ROOT+ "/staff/create")
    public StaffDto save(@RequestBody StaffDto dto) {

        return staffservice.save(dto);
    }


    @GetMapping(value =APP_ROOT+"/staff/{id_User}")
    public StaffDto findById(Integer id ) {

        return staffservice.findById(id);
    }

    @GetMapping(value =APP_ROOT+"/staff/firstname")
    public StaffDto findByFirstName(String firstname) {

        return staffservice.findByFirstName(firstname);
    }
    @GetMapping(value =APP_ROOT+"/staff/lastname")
    public StaffDto findByLastName(String lastname) {

        return staffservice.findByLastName(lastname);
    }
    @GetMapping(value =APP_ROOT+"/staff/email")
    public StaffDto findByEmail(String email) {

        return staffservice.findByEmail(email);
    }
    @GetMapping(value =APP_ROOT+"/staff/password")
    public StaffDto findByPassword(String password) {

        return staffservice.findByPassword(password);
    }
    @GetMapping(value =APP_ROOT+"/staff/birth_date")
    public StaffDto findByBirthDate(Date birth_date) {

        return staffservice.findByBirthDate(birth_date);
    }


    @GetMapping(value =APP_ROOT+"/staff/phone")
    public StaffDto findByPhone(String phone) {

        return staffservice.findByPhone(phone);
    }
    @RequestMapping(value="/Staff", method=RequestMethod.GET)
    public List<StaffDto> findStaffDto(){
        return staffservice.findAll();
    }

    @DeleteMapping(value =APP_ROOT+"/order/delete/{id}" )
    public void delete(Integer id) {

        staffservice.delete(id);
    }


}
