package com.example.managingfoodreservation.services.impl;

import com.example.managingfoodreservation.Repository.CanteenworkerRepository;
import com.example.managingfoodreservation.Repository.impl.CanteenworkerRepositoryImpl;
import com.example.managingfoodreservation.dto.CanteenworkerDto;
import com.example.managingfoodreservation.exception.EntityNotFoundException;
import com.example.managingfoodreservation.exception.ErrorCodes;
import com.example.managingfoodreservation.exception.InvalidEntityException;
import com.example.managingfoodreservation.model.Canteenworker;
import com.example.managingfoodreservation.services.CanteenworkerService;
import com.example.managingfoodreservation.validator.Canteenworkervalidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public abstract class CanteenworkerServiceImpl implements CanteenworkerService {
    @Autowired
    private CanteenworkerRepository canteenworkerRepository;
    @Autowired
    public CanteenworkerServiceImpl(CanteenworkerRepository canteenworkerRepository){
        this.canteenworkerRepository=canteenworkerRepository;
    }

    @Override
    public CanteenworkerDto save(CanteenworkerDto dto) {
        List<String> errors= Canteenworkervalidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("The Canteenworker is not valid{}",dto);
            throw new InvalidEntityException("The Canteenworker is not valid ", ErrorCodes.CANTEENWORKER_NOT_FOUND,errors);
        }

        return CanteenworkerDto.fromEntity(
                canteenworkerRepository.save(
                        CanteenworkerDto.toEntity(dto)
                )
        );
    }




    @Override
    public CanteenworkerDto findById(Integer id) {
        if(id ==null){
            log.error("The Canteenworker's Id is invalid");
            return null;
        }

        Optional<Canteenworker> canteenworker =canteenworkerRepository.findById(id);
        CanteenworkerDto dto =  CanteenworkerDto.fromEntity(canteenworker.get());
        return Optional.of(dto).orElseThrow(()->
                new EntityNotFoundException(
                        "No Canteenworker with the Id ="+id+"exists", ErrorCodes.CANTEENWORKER_NOT_FOUND)
        );
    }



    @Override
    public CanteenworkerDto findByOrderPrice(Double OrderPrice) {
        if (!StringUtils.hasLength(OrderPrice.toString())){
            log.error("The order price is not found");
            return null;
        }
        Optional<Canteenworker> canteenworker=canteenworkerRepository.findByOrderPrice(OrderPrice);
        return Optional.of(CanteenworkerDto.fromEntity(canteenworker.get())).orElseThrow(()->new EntityNotFoundException(
                "No canteen worker with this OrderPrice"+OrderPrice+"is Found",ErrorCodes.CANTEENWORKER_NOT_FOUND));
    }


    @Override
    public CanteenworkerDto findByOrderTime(Instant OrderTime) {
        if (!StringUtils.hasLength(OrderTime.toString())) {
            log.error("The orderTime is not found");
            return null;
        }
        Optional<Canteenworker>canteenworker=canteenworkerRepository.findByOrderTime(OrderTime);
        return Optional.of(CanteenworkerDto.fromEntity(canteenworker.get())).orElseThrow(()->new EntityNotFoundException(
                "No canteen worker with this OrderTime"+OrderTime+"is Found",ErrorCodes.CANTEENWORKER_NOT_FOUND));
    }




    @Override
    public  List<CanteenworkerDto> findAll() {
        CanteenworkerRepository repository = new CanteenworkerRepositoryImpl();
        return repository.findAll()
                .stream()
                .map(CanteenworkerDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id==null){
            log.error("The canteenworker is null");
            return;
        }
        canteenworkerRepository.deleteById(id);
    }
}

