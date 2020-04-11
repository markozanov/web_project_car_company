package com.carcompany.web_project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="saloons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Saloon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saloonID;

    private String city;

    private Integer carCapacity;

    @OneToMany(mappedBy = "saloon", cascade = CascadeType.ALL)
    private List<Employee> employees;

    @OneToMany(mappedBy = "saloon", cascade = CascadeType.ALL)
    private List<Car> cars;

}
