package com.example.managingfoodreservation.model;


import com.example.managingfoodreservation.dto.DishDto;
import com.example.managingfoodreservation.dto.MenuDto;
import com.example.managingfoodreservation.exception.EntityNotFoundException;
import com.example.managingfoodreservation.exception.ErrorCodes;
import com.example.managingfoodreservation.exception.InvalidEntityException;
import com.example.managingfoodreservation.services.DishService;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertThrows;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DishTest {
    @Autowired
    private DishService service;
    @Test
    public void shouldSaveDishWithSuccess(){
        DishDto expectedDish =DishDto.builder()
                .id(1)
                .dishname("Pizza")
                .quantity(5)
                .menu(MenuDto.builder().build())
                .orderTime(Instant.parse("2023-03-08T09:15:00Z"))
                .id(2)
                .dishname("Sandwich")
                .quantity(10)
                .menu(MenuDto.builder().build())
                .orderTime(Instant.parse("2023-03-08T09:00:00Z"))
                .id(3)
                .dishname("Paté")
                .quantity(7)
                .menu(MenuDto.builder().build())
                .orderTime(Instant.parse("2023-03-10T09:30:00Z"))
                .id(4)
                .dishname("Salade")
                .quantity(4)
                .menu(MenuDto.builder().build())
                .orderTime(Instant.parse("2023-03-12T08:30:00Z"))
                .id(5)
                .dishname("Lasagnes")
                .quantity(3)
                .menu(MenuDto.builder().build())
                .orderTime(Instant.parse("2023-03-06T08:45:00Z"))
                .build();


        DishDto savedDish =service.save(expectedDish);
        Assertions.assertNotNull(savedDish);
        Assertions.assertNotNull(savedDish.getId());
        Assertions.assertEquals(expectedDish.getMenu(),savedDish.getMenu());
        Assertions.assertNotNull(expectedDish.getDishname(),savedDish.getDishname());
        Assertions.assertNotNull(expectedDish.getOrderTime(), String.valueOf(savedDish.getOrderTime()));
        Assertions.assertNotNull(expectedDish.getQuantity(), String.valueOf(savedDish.getQuantity()));

    }
@Test
    public void shouldUpdateDishWithSuccess(){
    DishDto expectedDish =DishDto.builder()
            .id(1)
            .dishname("Pizza")
            .quantity(5)
            .menu(MenuDto.builder().build())
            .id(2)
            .dishname("Sandwich")
            .quantity(10)
            .menu(MenuDto.builder().build())
            .orderTime(Instant.parse("2023-03-08T09:00:00Z"))
            .id(3)
            .dishname("Paté")
            .quantity(7)
            .menu(MenuDto.builder().build())
            .orderTime(Instant.parse("2023-03-10T09:30:00Z"))
            .id(4)
            .dishname("Salade")
            .quantity(4)
            .menu(MenuDto.builder().build())
            .orderTime(Instant.parse("2023-03-12T08:30:00Z"))
            .id(5)
            .dishname("Lassagnes")
            .quantity(3)
            .menu(MenuDto.builder().build())
            .orderTime(Instant.parse("2023-03-06T08:45:00Z"))
            .build();


    DishDto savedDish =service.save(expectedDish);
    DishDto dishToUpdate=savedDish;
    dishToUpdate.setId(9);


    savedDish =service.save(dishToUpdate);
    Assertions.assertNotNull(dishToUpdate);
    Assertions.assertNotNull(dishToUpdate.getId());
    Assertions.assertEquals(dishToUpdate.getMenu(),savedDish.getMenu());
    Assertions.assertNotNull(dishToUpdate.getDishname(),savedDish.getDishname());
    Assertions.assertNotNull(dishToUpdate.getOrderTime(), String.valueOf(savedDish.getOrderTime()));
    Assertions.assertNotNull(dishToUpdate.getQuantity(), String.valueOf(savedDish.getQuantity()));

}

    @Test
    public void shouldThrowInvalidEntityException(){
        DishDto expectedDish =DishDto.builder().build();

        InvalidEntityException expectedException = assertThrows(InvalidEntityException.class,()->service.save(expectedDish));

        Assertions.assertEquals(ErrorCodes.DISH_NOT_VALID, expectedException.getErrorCode());
        Assertions.assertEquals(1,expectedException.getErrors().size());
        Assertions.assertEquals("Let us know the dish's id",expectedException.getErrors().get(0));

    }
    @Test
    public void shouldThrowEntityNotFoundException(){

        EntityNotFoundException expectedException = assertThrows(EntityNotFoundException.class,()->service.findById(0));

        Assertions.assertEquals(ErrorCodes.DISH_NOT_FOUND, expectedException.getErrorCode());
        Assertions.assertEquals("No Dish with the Id =0 exists",expectedException.getMessage());

    }
    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowEntityNotFoundException2(){
    service.findById(0);
    }
}