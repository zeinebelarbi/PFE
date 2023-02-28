package com.example.managingfoodreservation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;
import java.util.List;

@Data
@Builder

@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name="Company")
public class Company extends AbstractEntity {
    @Column (name="id_company")
    private Integer id ;
    @Column(name="Staffnumber")
        private int Staffnumber;
       /* @OneToOne(mappedBy ="idCompany")
        private Menu menu;
        @OneToMany(mappedBy ="Company")
        private List<Staff>staffnames;*/

        Company(Integer id, Instant creationDate, Instant lastUpdateDate) {
                super(id, creationDate, lastUpdateDate);
        }


        public Company() {
                super();
        }

    public void setStaffNumber(int staffNumber) {
    }

        public int getstaffNumber() {
                return getstaffNumber();
        }
}
