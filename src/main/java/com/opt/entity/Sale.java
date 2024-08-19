package com.opt.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Data
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int route;
    private double totalSaleAmount;
    @Temporal(TemporalType.DATE)
    private Date saleDate;


}