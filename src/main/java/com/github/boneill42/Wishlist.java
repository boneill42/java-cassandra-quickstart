package com.github.boneill42;

import java.util.List;

import com.netflix.astyanax.annotations.Component;

public class Wishlist {
    @Component(ordinal = 0)
    public String tenant;
    @Component(ordinal = 1)
    public String country;
    @Component(ordinal = 2)
    public String state;
    @Component(ordinal = 3)
    public String zip;
    @Component(ordinal = 4)
    public String userId;
    @Component(ordinal = 5)
    public List<String> wishlist;   
}