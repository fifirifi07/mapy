package com.example.aib.restauwacja.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailAndPassword {
    public String email;
    public String password;
}
