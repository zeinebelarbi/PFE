package com.example.managingfoodreservation.Repository.impl;

import com.example.managingfoodreservation.Repository.StaffRepository;
import com.example.managingfoodreservation.model.Staff;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class StaffRepositoryImpl implements StaffRepository {
    @Override
    public Optional<Staff> findByBirthDate(Date birthDate) {
        return Optional.empty();
    }

    @Override
    public Optional<Staff> findByFirstName(String firstname) {
        return Optional.empty();
    }

    @Override
    public Optional<Staff> findByLastName(String lastname) {
        return Optional.empty();
    }

    @Override
    public Optional<Staff> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<Staff> findByPassword(String password) {
        return Optional.empty();
    }

    @Override
    public Optional<Staff> findByOrder(Staff staff) {
        return Optional.empty();
    }


    @Override
    public Optional<Staff> findByPhone(String phone) {
        return Optional.empty();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Staff> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Staff> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Staff> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Staff getOne(Integer integer) {
        return null;
    }

    @Override
    public Staff getById(Integer integer) {
        return null;
    }

    @Override
    public Staff getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Staff> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Staff> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Staff> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Staff> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Staff> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Staff> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Staff, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Staff> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Staff> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Staff> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<Staff> findAll() {
        return null;
    }

    @Override
    public List<Staff> findAllById(Iterable<Integer> integers) {
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
    public void delete(Staff entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Staff> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Staff> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Staff> findAll(Pageable pageable) {
        return null;
    }
}
