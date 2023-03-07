package com.example.managingfoodreservation.services;

import com.example.managingfoodreservation.Repository.DishRepository;
import com.example.managingfoodreservation.Repository.MenuRepository;
import com.example.managingfoodreservation.Repository.impl.DishRepositoryImpl;
import com.example.managingfoodreservation.dto.DishDto;
import com.example.managingfoodreservation.dto.MenuDto;
import com.example.managingfoodreservation.exception.EntityNotFoundException;
import com.example.managingfoodreservation.exception.ErrorCodes;
import com.example.managingfoodreservation.exception.InvalidEntityException;

import com.example.managingfoodreservation.model.Menu;
import com.example.managingfoodreservation.validator.Menuvalidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public abstract class MenuServiceImpl implements MenuService{
    private MenuRepository menuRepository;
    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository){

        this.menuRepository=menuRepository;
    }
    @Override
    public MenuDto save(MenuDto dto) {
        List<String> errors= Menuvalidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("The Menu is not valid{}",dto);
            throw new InvalidEntityException("The Menu is not valid ", ErrorCodes.MENU_NOT_VALID,errors);
        }

        return MenuDto.fromEntity(
                menuRepository.save(
                        MenuDto.toEntity(dto)
                )
        );
    }




    @Override
    public MenuDto findById(Integer id) {
        if(id ==null){
            log.error("The Menu's Id is invalid");
            return null;
        }

        Optional<Menu> menu =menuRepository.findById(id);
        MenuDto dto =  MenuDto.fromEntity(menu.get());
        return Optional.of(dto).orElseThrow(()->
                new EntityNotFoundException(
                        "No Menu with the Id ="+id+"exists", ErrorCodes.MENU_NOT_FOUND)
        );
    }

    @Override
    public  List<DishDto> findAll() {
        DishRepository repository = new DishRepositoryImpl();
        return repository.findAll()
                .stream()
                .map(DishDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id==null){
            log.error("The menu is null");
            return;
        }
        menuRepository.deleteById(id);
    }

}
