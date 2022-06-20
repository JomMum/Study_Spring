package com.example.sample.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import javax.persistence.*;

@Entity
@Getter
@ToString
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 100, unique = true)
    String name;

    @Builder
    public Category(String name)
    {
        this.name = name;
    }
}
