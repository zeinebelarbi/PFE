package com.example.managingfoodreservation.controller.Api;


import com.example.managingfoodreservation.dto.DishDto;
import com.example.managingfoodreservation.dto.MenuDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.example.managingfoodreservation.utils.Constants.APP_ROOT;

public interface MenuApi {
    @PostMapping(value =APP_ROOT+ "/menu/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    MenuDto save(MenuDto dto);
    @GetMapping(value =APP_ROOT+"/menu/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    MenuDto findById(Integer id);
    @GetMapping(value =APP_ROOT+"/menu/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<DishDto> findAll();
    @DeleteMapping(value =APP_ROOT+"/menu/delete/{id}" )

    void delete(Integer id);
}
