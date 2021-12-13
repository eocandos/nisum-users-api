package com.nisum.users.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq")
    private Integer id;
    private String cityCode;
    private String countryCode;
    private Integer phoneNumber;

}
