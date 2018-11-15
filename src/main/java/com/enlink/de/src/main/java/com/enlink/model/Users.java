package com.enlink.de.src.main.java.com.enlink.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "users")
public class Users extends BaseModel {
    private static final long serialVersionUID = 1L;
    private String name;
    private String username;
    private String password;
    private String confits;
}
