package com.example.managingfoodreservation.dto;

import com.example.managingfoodreservation.model.Company;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;


import java.util.List;

@Builder
@Data

public class CompanyDto {
    private Integer id ;
    private int staffNumber;
    private MenuDto menu;
    @JsonIgnore
    private List<StaffDto> staffnames;

    public CompanyDto toEntity(Company company) {
        if (company == null) {
            return null;
        }
        return CompanyDto.builder()
                .id(company.getId())
                .staffNumber(company.getstaffNumber())
                .build();
    }

    public Company toEntity(CompanyDto companyDto) {
        if (companyDto == null) {
            return null;
        }
        Company company = new Company();
        company.setId(companyDto.getId());
        company.setStaffNumber(companyDto.getStaffNumber());
        return company;

    }
}

