package com.tamdao.api1.dto;

import lombok.Data;

@Data
public class UpdateHotelRequest {
    private String hotelName;
    private boolean status ;
}
