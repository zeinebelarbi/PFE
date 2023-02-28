package com.example.managingfoodreservation.validator;



import com.example.managingfoodreservation.dto.CompanyDto;

import java.util.ArrayList;
import java.util.List;

public class Companyvalidator {
    public static List<String> validate(CompanyDto companyDto) {
        List<String> errors = new ArrayList<>();
        if (companyDto== null){
        }
        if (companyDto.getMenu()==null){
           errors.add("Let us know the menu of the company ");
        }
        if (companyDto.getStaffnames()==null){
            errors.add("Let us know the Staff's name ");
        }
        if (companyDto.getStaffNumber()== 0){
            errors.add("Let us know the Staff's number");
        }
        return errors;
    }

}
