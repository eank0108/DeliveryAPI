package com.delivery.deliveryapi.model;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class Timestamped {

    @CreatedDate
    private long createdAt = System.currentTimeMillis();

    // @LastModifiedDate
    // private long modifiedAt = System.currentTimeMillis();
}
