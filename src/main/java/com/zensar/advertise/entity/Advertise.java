package com.zensar.advertise.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "Advertise")
@Table(name = "advertise")
public class Advertise extends BaseEntity{

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Double price;

    @Column(name = "category")
    private Integer category;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;
}
