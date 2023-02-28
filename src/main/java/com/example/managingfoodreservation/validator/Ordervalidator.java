package com.example.managingfoodreservation.validator;

import com.example.managingfoodreservation.dto.OrderDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Ordervalidator {
    public static List<String>validate(OrderDto orderdto){
        List<String> errors = new ArrayList<>();
        if(orderdto==null){
            errors.add("Let us know the order's name");
            errors.add("Let us know the order's date");
            errors.add("Let us know the order's number");
            errors.add("Let us know the order's staff member");
            errors.add("Let us know the orders");
        }
        if (!StringUtils.hasLength(orderdto.getName())){
            errors.add("Let us know the order's name");
        }
        if (orderdto.getDate()==null) {
            errors.add("Let us know the order's date");
        }
        if (orderdto.getNumber()==null) {
            errors.add("Let us know the order's number");
        }
        if(orderdto.getStaff()==null){
            errors.add("Let us know the order's staff member");
        }
        if(orderdto.getOrders()==null){
            errors.add("Let us know the orders");
        }

        return errors;
    }
}
