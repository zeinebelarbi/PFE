package com.example.managingfoodreservation.validator;

import com.example.managingfoodreservation.dto.ChefDto;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Chefvalidator {
    public static List<String> validate(ChefDto chefDto) {
        List<String> errors = new ArrayList<>();
        if (chefDto==null){
            errors.add("Let us know the Chef's name");
            errors.add("Let us know the Chef's associated canteen worker");
            errors.add("Let us know the Chef's prepared order");
            errors.add("Let us know the Chef's Deliver Time ");
        }
        if (!StringUtils.hasLength(chefDto.getName())) {
            errors.add("Let us know the Chef's name");
        }
        if (chefDto.getCanteenworker()==null) {
            errors.add("Let us know the Chef's associated canteen worker");
        }
        if (chefDto.getDeliverTime()==null) {
            errors.add("Let us know the Chef's Deliver Time ");
        }
        return errors;
    }
}