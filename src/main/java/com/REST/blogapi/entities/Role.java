package com.REST.blogapi.entities;

@Entity
public class Role {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    private Sting name;
}
