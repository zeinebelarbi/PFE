package com.example.managingfoodreservation.validator;


import com.example.managingfoodreservation.dto.ListofOrdersDto;
import com.example.managingfoodreservation.model.ListofOrders;

import java.util.ArrayList;
import java.util.List;

public class ListofOrdersvalidator {
    public static List<String> validate(ListofOrdersDto listofOrdersDto) {
        List<String> errors = new ArrayList<>();

        if (listofOrdersDto.getOrder() == null) {
            errors.add("Let us know the orders contained in the list ");
        }
        if (listofOrdersDto.getCanteenworkername() == null) {
            errors.add("Let us know the canteen worker's name ");
        }
        return errors;
    }
}
