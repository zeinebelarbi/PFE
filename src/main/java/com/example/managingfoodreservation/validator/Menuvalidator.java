package com.example.managingfoodreservation.validator;



import com.example.managingfoodreservation.dto.MenuDto;

import java.util.ArrayList;
import java.util.List;

public class Menuvalidator {
    public static List<String> validate(MenuDto menuDto) {

        List<String> errors = new ArrayList<>();
        if (menuDto == null) {
            errors.add("Let us know the company's name");
        }

        if (menuDto.getDishes()== null){
            errors.add("Let us know the dish's name");
        }
        return errors;
    }
}