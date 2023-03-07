package com.example.managingfoodreservation.Repository.impl;

import com.example.managingfoodreservation.Repository.MenuRepository;
import com.example.managingfoodreservation.model.Menu;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MenuRepositoryImpl implements MenuRepository {
    @Override
    public void flush() {

    }

    @Override
    public <S extends Menu> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Menu> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Menu> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Menu getOne(Integer integer) {
        return null;
    }

    @Override
    public Menu getById(Integer integer) {
        return null;
    }

    @Override
    public Menu getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Menu> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Menu> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Menu> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Menu> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Menu> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Menu> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Menu, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Menu> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Menu> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Menu> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<Menu> findAll() {
        return null;
    }

    @Override
    public List<Menu> findAllById(Iterable<Integer> integers) {
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
    public void delete(Menu entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Menu> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Menu> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Menu> findAll(Pageable pageable) {
        return null;
    }
}
