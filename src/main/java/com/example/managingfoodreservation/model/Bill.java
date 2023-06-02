package com.example.managingfoodreservation.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
@NamedQuery(name = "Bill.getAllBills",query="select b from Bill b order by b.id desc ")
@NamedQuery(name = "Bill.getBillByUserName",query="select b from Bill b where b.createdby=:username order by b.idbill desc" )
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="Bill")
public class Bill implements Serializable {
    private static final long  serialVersionUID =1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name ="id")
    private Integer idbill;

    @Column(name ="billname")
    private String billname;
    @Column(name ="username")
    private String username;
    @Column(name ="uuid")
    private String uuid;


    @Column(name ="payementmethod")
    private String payementmethod;
    @Column(name ="total")
    private Integer total;
    @Column(name="dishDetails",columnDefinition ="json" )
    private String dishDetail;
    @Column(name="createdby" )
    private String createdby;
    @Column(name="email" )
    private String email;
    @Column(name="quantity" )
    private String quantity;
}