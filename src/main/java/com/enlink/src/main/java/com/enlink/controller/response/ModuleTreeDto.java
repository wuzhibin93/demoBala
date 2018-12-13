package com.enlink.src.main.java.com.enlink.controller.response;

import lombok.Data;

import java.util.List;

@Data
public class ModuleTreeDto {
    private Integer id;
    private String name;
    private String configs;
    private String create_at;
    private String update_at;
    private List<ModuleTreeDto> children;
}
