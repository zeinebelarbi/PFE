package com.example.managingfoodreservation.services.impl;
import com.example.managingfoodreservation.Repository.DishRepository;
import com.example.managingfoodreservation.Repository.impl.DishRepositoryImpl;
import com.example.managingfoodreservation.dto.DishDto;
import com.example.managingfoodreservation.exception.EntityNotFoundException;
import com.example.managingfoodreservation.exception.ErrorCodes;
import com.example.managingfoodreservation.exception.InvalidEntityException;
import com.example.managingfoodreservation.model.Dish;
import com.example.managingfoodreservation.services.DishService;
import com.example.managingfoodreservation.validator.Dishvalidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public abstract class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;

    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public DishDto save(DishDto dto) {
        List<String> errors= Dishvalidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("The Dish is not valid{}",dto);
            throw new InvalidEntityException("The Dish is not valid ", ErrorCodes.DISH_NOT_VALID,errors);
        }

        return DishDto.fromEntity(
                dishRepository.save(
                        DishDto.toEntity(dto)
                )
        );
    }

    @Override
    public DishDto findById(Integer id) {
        if(id ==null){
            log.error("The Dish's Id is invalid");
            return null;
        }

        Optional<Dish> dish = dishRepository.findById(id);
        DishDto dto = DishDto.fromEntity(dish.get());
        return Optional.of(dto).orElseThrow(()->
                new EntityNotFoundException(
                        "No Dish with the Id ="+id+"exists", ErrorCodes.DISH_NOT_FOUND)
        );
    }

    @Override
    public List<DishDto> findAll() {
        DishRepository repository = new DishRepositoryImpl();
        return repository.findAll()
                .stream()
                .map(DishDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id==null){
            log.error("The Dish is null");
            return;
        }
        dishRepository.deleteById(id);
    }


    @Override
    public DishDto findByDishName(String dishname) {
        if (!StringUtils.hasLength(dishname)) {
            log.error("The order's name is Null");
            return null;
        }
        return dishRepository.findByDishName(dishname)
                .map(DishDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException(
                        "No order with the Name has been found "+dishname,ErrorCodes.DISH_NOT_FOUND
                ));
    }

    @Override
    public DishDto findByQuantity(Integer quantity) {
        if (!StringUtils.hasLength(quantity.toString())){
            log.error("The quantity is not found");
            return null;
        }
        Optional<Dish> dish=dishRepository.findByQuantity(quantity);
        return Optional.of(DishDto.fromEntity(dish.get())).orElseThrow(()->new EntityNotFoundException(
                "No canteen worker with this Quantity"+quantity+"is Found",ErrorCodes.DISH_NOT_FOUND));
    }
}
