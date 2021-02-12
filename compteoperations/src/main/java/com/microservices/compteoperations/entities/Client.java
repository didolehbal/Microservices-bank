package com.microservices.compteoperations.entities;

import lombok.Data;

@Data
public class Client {
    private Long code;
    private String name;
    private String email;
}
