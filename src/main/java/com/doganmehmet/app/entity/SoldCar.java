package com.doganmehmet.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sold_cars", uniqueConstraints = {@UniqueConstraint(columnNames = {"gallerist_id", "car_id", "customer_id"},
name = "UK_GALLERIST_CAR_CUSTOMER")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SoldCar extends BaseEntity {

    @ManyToOne
    private Gallerist gallerist;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Customer customer;
}
