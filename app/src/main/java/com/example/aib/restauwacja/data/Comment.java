package com.example.aib.restauwacja.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {
    public Integer id;
    public Integer restaurantId;
    public Integer ownerId;
    public String text;
    public int rating;
    public String created;
    @JsonIgnore
    public String author;
}
