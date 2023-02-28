package com.example.managingfoodreservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;
@Data

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable {


    @Id
    @GeneratedValue()
    private Integer id;
    @CreatedDate
    @Column(name="creationDate")
    @JsonIgnore
    Instant creationDate;
    @LastModifiedDate
    @Column(name="lastModifiedDate")
    @JsonIgnore
    private Instant  lastUpdateDate;

    public AbstractEntity() {
    }
    public AbstractEntity(Integer id, Instant creationDate, Instant lastUpdateDate) {
        this.id = id;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
    }

}

