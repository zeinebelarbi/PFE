package com.example.managingfoodreservation.controller;


import com.example.managingfoodreservation.dto.DishDto;
import com.example.managingfoodreservation.model.Menu;
import com.example.managingfoodreservation.services.DishService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;



import java.time.Instant;
import java.util.List;

import static com.example.managingfoodreservation.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT+"/dishes")
public abstract class DishController  {

    private DishService dishService;

    @Autowired
    public DishController( DishService dishService){
        this.dishService=dishService;

    }

    @PostMapping(value =APP_ROOT+ "/create")
    @ApiOperation(value ="Save a dish",notes = "This method allow to add or to update a dish ",response= DishDto.class)
    @ApiResponses(value ={
            @ApiResponse(code=200,message="The dish is been created or updated" ),
            @ApiResponse(code = 400, message = "The dish is not valid ")
    })
    public DishDto save(@RequestBody DishDto dto) {

        return dishService.save(dto);
    }

    @GetMapping(value =APP_ROOT+"/dish/{id}")
    @ApiOperation(value ="Search for a dish by its Id ",notes = "This method allow to search for a dish using its Id ",response= DishDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The dish is found in the database"),
            @ApiResponse(code = 404, message = "The searched dish by its Id is not found ")
    })
    public DishDto findById(Integer id) {
        
        return dishService.findById(id);
    }

    @GetMapping(value =APP_ROOT+"/dish/{Dish name}")
    @ApiOperation(value ="Search for a dish by its Name ",notes = "This method allow to search for a dish using its Name ",response= DishDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The dish is found in the database"),
            @ApiResponse(code = 404, message = "The searched dish by its Name is not found ")
    })
    public DishDto findByDishName(String dishname) {
        return dishService.findByDishName(dishname);
    }

    @GetMapping(value =APP_ROOT+"/dish/{Quantity}")
    @ApiOperation(value ="Search for a dish by its Quantity ",notes = "This method allow to search for a dish using its Quantity ",response= DishDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The dish is found in the database"),
            @ApiResponse(code = 404, message = "The searched dish by its Quantity is not found "),
    })
    public DishDto findByQuantity(Integer quantity) {
        return dishService.findByQuantity(quantity);
    }


    @GetMapping(value =APP_ROOT+"/dish/{menu}")
    @ApiOperation(value ="Search for a dish in the Menu ",notes = "This method allow to search for a dish using the Menu",response= DishDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The dish is found in the database"),
            @ApiResponse(code = 404, message = "The searched dish is not found in the Menu ")
    })
    public DishDto findByMenu(Menu menu) {

        return dishService.findByMenu (menu);
    }


    @GetMapping(value =APP_ROOT+"/canteenworker/{OrderTime}")
    @ApiOperation(value ="Search for a dish by its OrderTime",notes = "This method allow to search for a dish using its OrderTime ",response= DishDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The dish is found in the database"),
            @ApiResponse(code = 404, message = "The searched dish by its OrderTime is not found")
    })
    public DishDto findByOrderTime(Instant orderTime) {

        return dishService.findByOrderTime(orderTime);
    }

    @GetMapping(value=APP_ROOT+"/dish/all")

    @ApiOperation(value ="Give the list of dishes",notes = "This method allow to search and give the list of available dishes ",responseContainer ="List<DishDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The List of dishes / An Empty List"),
    })
    public List<DishDto> findAll() {
        return dishService.findAll();
    }



    @DeleteMapping(value =APP_ROOT+"/dish/delete/{id}" )
    @ApiOperation(value ="Delete a dish",notes = "This method allow to delete a dish by its Id ",response= DishDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The dish is deleted "),
    })
    public void delete(Integer id) {

        dishService.delete(id);
    }
}
