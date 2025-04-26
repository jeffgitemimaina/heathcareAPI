package com.healthcare_api.healthcare_api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;
}