package com.example.managingfoodreservation.services.impl;
import com.example.managingfoodreservation.Repository.StaffRepository;
import com.example.managingfoodreservation.dto.StaffDto;
import com.example.managingfoodreservation.exception.EntityNotFoundException;
import com.example.managingfoodreservation.exception.ErrorCodes;
import com.example.managingfoodreservation.exception.InvalidEntityException;
import com.example.managingfoodreservation.model.Staff;
import com.example.managingfoodreservation.services.StaffService;
import com.example.managingfoodreservation.validator.Staffvalidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public abstract class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository) {

        this.staffRepository = staffRepository;

    }


    @Override
    public StaffDto save(StaffDto dto) {
        List<String> errors= Staffvalidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("The staff is not valid{}",dto);
            throw new InvalidEntityException("The staff is not valid ", ErrorCodes.STAFF_NOT_VALID,errors);
        }

        return StaffDto.fromEntity(
                staffRepository.save(
                        StaffDto.toEntity(dto)
                )
        );
    }

    @Override
    public StaffDto findByFirstName(String firstname) {
        if (!StringUtils.hasLength(firstname)) {
            log.error("The staff's firstname is not found");
            return null;
        }
        Optional<Staff> staff=staffRepository.findByFirstName(firstname);
        return Optional.of(StaffDto.fromEntity(staff.get())).orElseThrow(()->new EntityNotFoundException(
                "No canteen worker with this firstname"+firstname+"is Found",ErrorCodes.STAFF_NOT_FOUND));

    }
    @Override
    public StaffDto findByLastName(String lastname) {
        if (!StringUtils.hasLength(lastname)) {
            log.error("The staff's lastname is not found");
            return null;
        }
        Optional<Staff> staff=staffRepository.findByLastName(lastname);
        return Optional.of(StaffDto.fromEntity(staff.get())).orElseThrow(()->new EntityNotFoundException(
                "No canteen worker with this lastname"+lastname+"is Found",ErrorCodes.STAFF_NOT_FOUND));

    }

    @Override
    public StaffDto findById(Integer id) {
        if(id == null){
            log.error("The staff's Id is Null");
            return null;
        }
        return staffRepository.findById(id)
                .map(StaffDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException(
                        "No staff with the Id has been found "+id,ErrorCodes.STAFF_NOT_FOUND
                ));
    }

    @Override
    public StaffDto findByEmail(String email) {
        if (!StringUtils.hasLength(email)) {
            log.error("The staff's email is Null");
            return null;
        }
        Optional<Staff> staff=staffRepository.findByEmail(email);
        return Optional.of(StaffDto.fromEntity(staff.get())).orElseThrow(()->new EntityNotFoundException(
                "No canteen worker with this email "+email+"is Found",ErrorCodes.STAFF_NOT_FOUND));
    }

    @Override
    public StaffDto findByPassword(String password) {
        if (!StringUtils.hasLength(password)) {
            log.error("The staff's password is not found");
            return null;
        }
        Optional<Staff> staff=staffRepository.findByPassword(password);
        return Optional.of(StaffDto.fromEntity(staff.get())).orElseThrow(()->new EntityNotFoundException(
                "No canteen worker with this password"+password+"is Found",ErrorCodes.STAFF_NOT_FOUND));

    }

    @Override
    public StaffDto findByBirthDate(Date birth_date) {
        if (!StringUtils.hasLength(birth_date.toString())) {
            log.error("The birth_date is not found");
            return null;
        }
        Optional<Staff> staff=staffRepository.findByBirthDate(birth_date);
        return Optional.of(StaffDto.fromEntity(staff.get())).orElseThrow(()->new EntityNotFoundException(
                "No canteen worker with this birth_date"+birth_date+"is Found",ErrorCodes.STAFF_NOT_FOUND));

    }

    @Override
    public StaffDto findByPhone(String phone) {
        if (!StringUtils.hasLength(phone)) {
            log.error("The staff's phone  is not found");
            return null;
        }
        Optional<Staff> staff=staffRepository.findByPhone(phone);
        return Optional.of(StaffDto.fromEntity(staff.get())).orElseThrow(()->new EntityNotFoundException(
                "No canteen worker with this phone "+phone+"is Found",ErrorCodes.STAFF_NOT_FOUND));

    }


    @Override
    public void delete(Integer id_order) {
        if(id_order==null){
            log.error("The Order is null");
            return;
        }
        staffRepository.deleteById(id_order);

    }
}
