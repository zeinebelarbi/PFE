package com.example.managingfoodreservation.services;

import com.example.managingfoodreservation.dto.MenuDto;

import java.util.List;

public interface MenuService {
    MenuDto save(MenuDto dto);

    MenuDto findById(Integer id);

    List<MenuDto> findAll();

    void delete(Integer id);
}
