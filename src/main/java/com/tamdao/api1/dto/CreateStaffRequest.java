package com.tamdao.api1.dto;

import lombok.Data;

@Data
public class CreateStaffRequest {
    private String id;
    private String name;
    private String age;
    private String email;
}
