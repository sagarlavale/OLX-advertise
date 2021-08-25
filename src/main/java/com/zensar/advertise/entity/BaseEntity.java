package com.zensar.advertise.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt = new Date();

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt = new Date();

    @Column(nullable = false,name = "created_by_id")
    private Integer createdById;

    @Column(nullable = false,name = "created_by")
    private String createdBy;

    @Column(nullable = false,name = "updated_by_id")
    private Integer updatedById;

    @Column(nullable = false,name = "updated_by")
    private String updatedBy;
}
