package com.chenx.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = -6606358986722205682L;
    private Long id;
    private String username;
    @JsonIgnoreProperties
    private String password;
    private Integer identity;
}
