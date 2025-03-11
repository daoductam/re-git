package com.tamdao.api1.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "hotel")
@Data
public class Hotel {
    //1.@id - primary
    //2. no-arg constructor
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long hotelId;

    @Column(name = "name")
    private String hotelName;

    @Column(name = "status")
    private boolean status;

    @Transient
    private Integer rate;//1-5star

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id",referencedColumnName ="id" )
    private Address address;

}
