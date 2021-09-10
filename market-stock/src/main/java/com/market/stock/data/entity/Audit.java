package com.market.stock.data.entity;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Data;
import lombok.ToString;

@MappedSuperclass
@Data
@ToString
public abstract class Audit implements Serializable {

    private static final long serialVersionUID = 1L;


    @Column(name="CREATED_ON",nullable = false, updatable = false)
    private Instant createdOn;


    @PrePersist
    public void prePersist() {
        this.createdOn = Instant.now();
    }

}
