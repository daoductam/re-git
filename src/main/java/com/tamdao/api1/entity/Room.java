package com.tamdao.api1.entity;

import lombok.Data;

@Data
public class Room {
    private String roomId;
    private String roomName;
    private boolean status = true;
}
