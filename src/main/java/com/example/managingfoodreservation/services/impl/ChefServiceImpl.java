package com.example.managingfoodreservation.services.impl;

import com.example.managingfoodreservation.Repository.ChefRepository;


import com.example.managingfoodreservation.Repository.impl.ChefRepositoryImpl;

import com.example.managingfoodreservation.dto.ChefDto;

import com.example.managingfoodreservation.exception.EntityNotFoundException;
import com.example.managingfoodreservation.exception.ErrorCodes;
import com.example.managingfoodreservation.exception.InvalidEntityException;
import com.example.managingfoodreservation.model.Canteenworker;
import com.example.managingfoodreservation.model.Chef;
import com.example.managingfoodreservation.services.ChefService;

import com.example.managingfoodreservation.validator.Chefvalidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Slf4j
 @Service
public abstract class ChefServiceImpl implements ChefService {
    private final ChefRepository chefRepository;

    public ChefServiceImpl(ChefRepository chefRepository) {
        this.chefRepository = chefRepository;
    }

    @Override
    public ChefDto save(ChefDto dto) {
        List<String> errors= Chefvalidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("The Chef is not valid{}",dto);
            throw new InvalidEntityException("The Chef is not valid ", ErrorCodes.CHEF_NOT_VALID,errors);
        }

        return ChefDto.fromEntity(
                chefRepository.save(
                        ChefDto.toEntity(dto)
                )
        );
    }

    @Override
    public ChefDto findById(Integer id) {
        if(id ==null){
            log.error("The Chef 's Id is invalid");
            return null;
        }

        Optional<Chef> chef =chefRepository.findById(id);
        ChefDto dto =  ChefDto.fromEntity(chef.get());
        return Optional.of(dto).orElseThrow(()->
                new EntityNotFoundException(
                        "No Chef with the Id ="+id+"exists", ErrorCodes.CHEF_NOT_FOUND)
        );
    }

    @Override
    public ChefDto findByName(String name) {

        if (!StringUtils.hasLength(name)) {
            log.error("The chef's firstname is not found");
            return null;
        }
        Optional<Chef> chef=chefRepository.findByName(name);
        return Optional.of(ChefDto.fromEntity(chef.get())).orElseThrow(()->new EntityNotFoundException(
                "No chef with this name"+name+"is Found",ErrorCodes.CHEF_NOT_FOUND));

    }


    @Override
    public ChefDto findByCanteenworker(Canteenworker canteenworker) {

        if (canteenworker == null) {
            log.error("The canteenworker associated to the chef is Null");
            return null;
        }
        ChefDto chef=chefRepository.findByCanteenworker(canteenworker);
        return Optional.of(ChefDto.fromEntity(chef.get())).orElseThrow(()->new EntityNotFoundException(
                "No canteenworker associated to the chef "+canteenworker+"is Found",ErrorCodes.CHEF_NOT_FOUND));
    }



    @Override
    public ChefDto findByDeliverTime(Instant DeliverTime) {
        if (!StringUtils.hasLength(DeliverTime.toString())){
            log.error("The DeliverTime is not found");
            return null;
        }
        Optional<Chef> chef =chefRepository.findByDeliverTime(DeliverTime);
        return Optional.of(ChefDto.fromEntity(chef.get())).orElseThrow(()->new EntityNotFoundException(
                "No chef with this DeliverTime"+DeliverTime+"is Found",ErrorCodes.CHEF_NOT_FOUND));
    }

    @Override
    public List<ChefDto> findAll() {
        ChefRepository repository = new ChefRepositoryImpl();
        return repository.findAll()
                .stream()
                .map(ChefDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id==null){
            log.error("The chef is null");
            return;
        }
        chefRepository.deleteById(id);
    }
    }


