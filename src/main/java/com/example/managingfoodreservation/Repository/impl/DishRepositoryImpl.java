package com.example.managingfoodreservation.Repository.impl;

import com.example.managingfoodreservation.Repository.DishRepository;
import com.example.managingfoodreservation.model.Dish;
import com.example.managingfoodreservation.model.Menu;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class DishRepositoryImpl implements DishRepository {
    @Override
    public void flush() {

    }

    @Override
    public <S extends Dish> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Dish> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Dish> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Dish getOne(Integer integer) {
        return null;
    }

    @Override
    public Dish getById(Integer integer) {
        return null;
    }

    @Override
    public Dish getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Dish> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Dish> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Dish> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Dish> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Dish> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Dish> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Dish, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Dish> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Dish> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Dish> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<Dish> findAll() {
        return null;
    }

    @Override
    public List<Dish> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Dish entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Dish> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Dish> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Dish> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Dish> findByDishName(String dishname) {
        return Optional.empty();
    }

    @Override
    public Optional<Dish> findByQuantity(Integer quantity) {
        return Optional.empty();
    }


    @Override
    public Optional<Dish> findByMenu(Menu menu) {
        return Optional.empty();
    }

    @Override
    public Optional<Dish> findByorderTime(Instant orderTime) {
        return Optional.empty();
    }


}
