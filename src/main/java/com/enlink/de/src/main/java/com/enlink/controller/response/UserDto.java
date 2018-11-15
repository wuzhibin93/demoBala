package com.enlink.de.src.main.java.com.enlink.controller.response;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private Integer id;
    private String name;
    private String username;
    private Date create_at;
    private Date update_at;
}
