package com.example.managingfoodreservation.validator;

import com.example.managingfoodreservation.dto.DishDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Dishvalidator {
    public static List<String> validate(DishDto dishDto){
        List<String> errors = new ArrayList<>();
        if (dishDto==null){
            errors.add("Let us know the dish's name");
            errors.add("Let us know the dish's quantity");
            errors.add("Let us know the dish's orderTime");
            errors.add("Let us know the dish's menu");
        }
        if (!StringUtils.hasLength(dishDto.getDishname())){
            errors.add("Let us know the dish's name");
        }
        if(dishDto.getQuantity()==null){
            errors.add("Let us know the dish's quantity");
        }
        if(dishDto.getOrderTime()==null){
            errors.add("Let us know the dish's orderTime");
        }

        if (dishDto.getMenu()==null){
            errors.add("Let us know the dish's menu");
        }
return errors;
    }
}
