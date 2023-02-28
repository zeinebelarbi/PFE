package com.example.managingfoodreservation.dto;

import com.example.managingfoodreservation.model.Staff;
import lombok.Builder;
import lombok.Data;


import java.util.Date;
import java.util.List;

@Builder
@Data
public class StaffDto {
    private Integer id;

    private String Firstname;

    private String Lastname;

    private String email;

    private String password;

    private String phone;

    private Date birth_date;

    private List<OrderDto> orders;

    private CompanyDto Companyname;

    public StaffDto fromEntity(Staff staff) {
        if (staff == null) {
            return null;
        }
        return StaffDto.builder()
                .Firstname(staff.getFirstname())
                .Lastname(staff.getLastname())
                .email(staff.getEmail())
                .password(staff.getPassword())
                .birth_date(staff.getBirth_date())
                .phone(staff.getPhone())
                .build();
    }

    public Staff toEntity(StaffDto staffDto) {
        if (staffDto == null) {
            return null;
        }
        Staff staff = new Staff();
        staff.setFirstname(staff.getFirstname());
        staff.setLastname(staff.getLastname());
        staff.setEmail(staff.getEmail());
        staff.setPassword(staff.getPassword());
        staff.setBirth_date(staff.getBirth_date());
        staff.setPhone(staff.getPhone());
        return staff;

    }
}
