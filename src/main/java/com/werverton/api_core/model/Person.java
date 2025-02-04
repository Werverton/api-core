package com.werverton.api_core.model;

import jakarta.persistence.*;

import lombok.Data;


@Data
@Entity
@Table(name ="person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
}
