package com.nisum.users.api.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {

    private String mensaje;

    public Message(String message) {
        this.mensaje = message;
    }
}
