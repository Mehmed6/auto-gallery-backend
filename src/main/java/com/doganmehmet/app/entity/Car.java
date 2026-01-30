package com.doganmehmet.app.entity;

import com.doganmehmet.app.enums.CarStatusType;
import com.doganmehmet.app.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car extends BaseEntity{

    private String plaka;
    private String brand;
    private  String model;
    @Column(name = "production_year")
    private Integer productionYear;
    private BigDecimal price;
    @Column(name = "currency_type")
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;
    @Column(name = "damage_price")
    private BigDecimal damagePrice;
    @Column(name = "car_status_type")
    @Enumerated(EnumType.STRING)
    private CarStatusType carStatusType;
}
