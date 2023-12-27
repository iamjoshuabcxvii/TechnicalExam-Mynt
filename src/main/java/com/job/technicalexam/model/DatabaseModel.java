package com.job.technicalexam.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class DatabaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int priority;
    private String ruleName;
    private Double minWeight;
    private Double maxWeight;
    private Double minVolume;
    private Double maxVolume;
    private Double price;
//    private String multiplier;
}
