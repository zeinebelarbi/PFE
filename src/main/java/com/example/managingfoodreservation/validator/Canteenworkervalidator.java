package com.example.managingfoodreservation.validator;

import com.example.managingfoodreservation.dto.CanteenworkerDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Canteenworkervalidator {
    public static List<String> validate(CanteenworkerDto canteenworkerDto) {
        List<String> errors = new ArrayList<>();
        if (canteenworkerDto== null){
            errors.add("Please let us know the Canteen-worker's name");
            errors.add("Please let us know the Canteen-worker's OrderPrice");
            errors.add("Please let us know the Canteen-worker's OrderTime");
            errors.add("Please let us know the Canteen-worker's associated Chef ");
        }
        if (canteenworkerDto== null || !StringUtils.hasLength(canteenworkerDto.getName())) {
        errors.add("Please let us know the Canteen-worker's name");
        }
        if (canteenworkerDto.getOrderPrice()==null){
            errors.add("Please let us know the Canteen-worker's OrderPrice");
        }
        if(canteenworkerDto.getOrderTime()==null){
            errors.add("Please let us know the Canteen-worker's OrderTime");
        }
        if(canteenworkerDto.getChefs()==null){
            errors.add("Please let us know the Canteen-worker's associated Chef ");
        }

        return errors;
    }
}

