package com.tamdao.api1.entity;

import lombok.Data;


@Data
public class Hotel {
    private String hotelId;
    private String hotelName;
    private Integer rate;//1-5star
    private boolean status = true;
}
