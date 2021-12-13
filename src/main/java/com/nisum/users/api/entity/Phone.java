package com.nisum.users.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class Phone {

    @Id
    private Integer id;
    private String cityCode;
    private String countryCode;

}
