package com.example.managingfoodreservation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import java.time.Instant;
import java.util.Date;


@Data
@Builder

@AllArgsConstructor

@Entity
@Table (name="Staff")
public class Staff extends AbstractEntity {
    @Column (name="id_Staff")
    private Integer id ;
    @Column (name="Firstname" ,insertable=false, updatable=false)

    private String Firstname;
    @Column(name="Lastname",insertable=false, updatable=false)
    private String Lastname;
    @Embedded
    private String email;
    @Embedded
    private String password;
    @Column(name="phone",insertable=false, updatable=false)
    private String phone;
    @Column(name="birth_date",insertable=false, updatable=false)
    private Date birth_date;



    @ManyToOne
    @JoinColumn(name="idCompany",insertable=false, updatable=false)
    private Company Companyname ;

    Staff(Integer id, Instant creationDate, Instant lastUpdateDate) {

        super(id, creationDate, lastUpdateDate);
    }

    public Staff() {

        super();
    }
}
