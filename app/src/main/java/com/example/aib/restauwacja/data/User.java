package com.example.aib.restauwacja.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Olek on 2015-06-04.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {

    public Integer id;

    public String email;

    public String login;

    @JsonProperty("new_password")
    public String password;

    @JsonProperty("session_id")
    public String sessionId;

    @JsonProperty("display_name")
    public String displayName;

    @JsonProperty("first_name")
    public String firstName;

    @JsonProperty("last_name")
    public String lastName;
}
