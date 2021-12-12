package com.nisum.users.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class User {

    @Id
    private Integer id;
    private String user;
    private String password;
    private String token;

}