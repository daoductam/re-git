package com.tamdao.api1.dto;

import lombok.Data;


@Data
public class CreateHotelRequest {
    private String hotelId;

    private String hotelName;

    private Integer rate;
}



