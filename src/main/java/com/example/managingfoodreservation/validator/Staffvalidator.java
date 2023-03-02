package com.example.managingfoodreservation.validator;

import com.example.managingfoodreservation.dto.StaffDto;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class Staffvalidator {
    public static List<String> validate(StaffDto staffDto) {
        List<String> errors = new ArrayList<>();

        if (staffDto.getFirstname() == null){
            errors.add("Let us know the Staff's firstname");
        } else {
            if (!StringUtils.hasLength(staffDto.getFirstname())) {
                errors.add("The Staff's firstname field is required");
            }
        }
        if (staffDto.getLastname() == null){
            errors.add("Let us know the Staff's lastname");
        } else {
            if (!StringUtils.hasLength(staffDto.getLastname())) {
                errors.add("Let us know the Staff's lastname");
            }
        }
        if (staffDto.getEmail() == null) {
            errors.add("Let us know the Staff's email");
        } else {
            if (!StringUtils.hasLength(staffDto.getEmail())) {
                errors.add("The email Field is required ");
            }
        }
        if (staffDto.getPassword() == null) {
            errors.add("Let us know the Staff's password");
        } else {
            if (!StringUtils.hasLength(staffDto.getPassword())) {
                errors.add("Let us know the Staff's password");
            }
        }
        if (staffDto.getPhone() == null) {
            errors.add("Let us know the Staff's phone");
        } else {
            if (!StringUtils.hasLength(staffDto.getPhone())) {
                errors.add("The Staff's phone field is required");
            }
        }
        if (staffDto.getBirth_date() == null) {
            errors.add("Let us know the Staff's Birth_date");
        } else {
            if (!StringUtils.hasLength((CharSequence) staffDto.getBirth_date())) {
                errors.add("The Birth_date field is required");
            }
        }
            return errors;
        }

}


