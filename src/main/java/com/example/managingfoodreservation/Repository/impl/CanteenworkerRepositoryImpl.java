package com.example.managingfoodreservation.Repository.impl;

import com.example.managingfoodreservation.Repository.CanteenworkerRepository;
import com.example.managingfoodreservation.model.Canteenworker;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;


import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class CanteenworkerRepositoryImpl implements CanteenworkerRepository {
    @Override
    public <S extends Canteenworker> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Canteenworker> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Canteenworker> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<Canteenworker> findAll() {
        return null;
    }

    @Override
    public List<Canteenworker> findAllById(Iterable<Integer> integers) {
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
    public void delete(Canteenworker entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Canteenworker> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Optional<Canteenworker> findByOrderPrice(Double orderPrice) {
        return Optional.empty();
    }

    @Override
    public Optional<Canteenworker> findByOrderTime(Instant orderTime) {
        return Optional.empty();
    }

    @Override
    public Optional<Canteenworker> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Canteenworker> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Canteenworker> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Canteenworker> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Canteenworker getOne(Integer integer) {
        return null;
    }

    @Override
    public Canteenworker getById(Integer integer) {
        return null;
    }

    @Override
    public Canteenworker getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Canteenworker> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Canteenworker> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Canteenworker> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Canteenworker> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Canteenworker> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Canteenworker> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Canteenworker, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Canteenworker> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Canteenworker> findAll(Pageable pageable) {
        return null;
    }
}
