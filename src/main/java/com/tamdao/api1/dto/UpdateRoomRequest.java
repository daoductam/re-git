package com.tamdao.api1.dto;

import lombok.Data;

@Data
public class UpdateRoomRequest {
    private String roomName;
    private boolean status;
}
