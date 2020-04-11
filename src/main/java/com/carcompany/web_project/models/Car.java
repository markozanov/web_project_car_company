package com.carcompany.web_project.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="cars")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long car_id;

    private String name;

    private Integer year;

    private Integer horsepower;

    private Double enginecapacity;

    private VehicleType vehicletype;

    private String colour;

    private Integer price;

    private boolean sold = false;

    @OneToOne(mappedBy = "car")
    private Purchase purchase;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "saloon_id")
    private Saloon saloon;

}
