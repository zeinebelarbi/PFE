package com.example.managingfoodreservation.Repository.impl;

import com.example.managingfoodreservation.Repository.ChefRepository;
import com.example.managingfoodreservation.dto.ChefDto;
import com.example.managingfoodreservation.model.Canteenworker;
import com.example.managingfoodreservation.model.Chef;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ChefRepositoryImpl implements ChefRepository {
    @Override
    public Optional<Chef> findByDeliverTime(Instant deliverTime) {
        return Optional.empty();
    }

    @Override
    public Optional<Chef> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public ChefDto findByCanteenworker(Canteenworker canteenworker) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Chef> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Chef> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Chef> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Chef getOne(Integer integer) {
        return null;
    }

    @Override
    public Chef getById(Integer integer) {
        return null;
    }

    @Override
    public Chef getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Chef> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Chef> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Chef> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Chef> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Chef> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Chef> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Chef, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Chef> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Chef> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Chef> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<Chef> findAll() {
        return null;
    }

    @Override
    public List<Chef> findAllById(Iterable<Integer> integers) {
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
    public void delete(Chef entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Chef> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Chef> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Chef> findAll(Pageable pageable) {
        return null;
    }
}
