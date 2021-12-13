package com.nisum.users.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq")
    private Integer id;
    // private String user;
    // @JsonIgnore
    private String password;
    private String token;

    private String name;
    private String email;

    @OneToMany( cascade = CascadeType.ALL )
    private List<Phone> phones;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date modified;
}