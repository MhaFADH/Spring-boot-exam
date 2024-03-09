package com.tp.exam.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Entity(name = "parfum")
@Data
public class ParfumModel {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;
    private String titre;
    @Column(columnDefinition = "TEXT")
    private String description;

    private String miniDesc;

    private Double prix;

    @Column(columnDefinition = "TEXT")
    private String photo;


}
