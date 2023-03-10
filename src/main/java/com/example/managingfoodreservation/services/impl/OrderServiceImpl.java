package com.example.managingfoodreservation.services.impl;

import com.example.managingfoodreservation.Repository.OrderRepository;
import com.example.managingfoodreservation.Repository.StaffRepository;
import com.example.managingfoodreservation.dto.OrderDto;
import com.example.managingfoodreservation.exception.EntityNotFoundException;
import com.example.managingfoodreservation.exception.ErrorCodes;
import com.example.managingfoodreservation.exception.InvalidEntityException;
import com.example.managingfoodreservation.model.Order;
import com.example.managingfoodreservation.model.Staff;
import com.example.managingfoodreservation.services.OrderService;
import com.example.managingfoodreservation.validator.Ordervalidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j

public abstract class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private StaffRepository staffRepository;

@Autowired
    public OrderServiceImpl(OrderRepository orderRepository, StaffRepository staffRepository) {
        this.orderRepository = orderRepository;
        this.staffRepository = staffRepository;
    
    }

    @Override
    public OrderDto save(OrderDto dto) {
        List<String> errors = Ordervalidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("The order is not valid");
            throw new InvalidEntityException("The client 's order is invalid", ErrorCodes.ORDER_NOT_VALID, errors);
        }
        Optional<Staff> staff = staffRepository.findById(dto.getStaff().getId());

        if (staff.isEmpty()) {
            log.warn("The staff with ID{} was not Found", dto.getStaff().getId());
            throw new EntityNotFoundException("No Staff with the ID " + dto.getStaff().getId() + "is Found", ErrorCodes.STAFF_NOT_FOUND);
        }
        List<String> orderErrors = new ArrayList<>();


        if (!orderErrors.isEmpty()) {
            log.warn("");
            throw new InvalidEntityException("The order doesn't exist ", ErrorCodes.ORDER_NOT_FOUND, orderErrors);
        }
        Order savedorder = orderRepository.save(OrderDto.toEntity(dto));

            return OrderDto.fromEntity(savedorder);
        }


    @Override
    public OrderDto findByName(String name) {
        if (!StringUtils.hasLength(name)) {
            log.error("The order's name is Null");
            return null;
        }
        return orderRepository.findByName(name)
                .map(OrderDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException(
                        "No order with the Name has been found "+name,ErrorCodes.ORDER_NOT_FOUND
                ));
    }

    @Override
    public OrderDto findById(Integer id) {
    if(id == null){
        log.error("The order Id is Null");
        return null;
    }
        return orderRepository.findById(id)
                .map(OrderDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException(
                "No order with the Id has been found "+id,ErrorCodes.ORDER_NOT_FOUND
                ));
    }

    @Override
    public OrderDto findByDate(Instant date) {
        if (date == null) {
            log.error("The order Date is Null");
            return null;
        }
        return orderRepository.findByDate(date)
                .map(OrderDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException(
                        "No order with this date has been found "+date,ErrorCodes.ORDER_NOT_FOUND
                ));
    }

    @Override
    public OrderDto findByNumber(Integer number) {

        if (number == null) {
            log.error("The order's number is Null");
            return null;
        }
        return orderRepository.findByNumber(number)
                .map(OrderDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException(
                        "No order with this number has been found "+number,ErrorCodes.ORDER_NOT_FOUND
                ));
    }

 

    @Override
    public OrderDto findByStaff(Staff staff) {

        if (staff == null) {
            log.error("The staff associated to the order is Null");
            return null;
        }
        return orderRepository.findByStaff(staff)
                .map(OrderDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException(
                        "No order associated to the staff  has been found "+staff,ErrorCodes.ORDER_NOT_FOUND
                ));
    }



    @Override
    public void delete(Integer id) {
        if(id==null){
            log.error("The Order is null");
            return;
        }
        orderRepository.deleteById(id);
    }
}
