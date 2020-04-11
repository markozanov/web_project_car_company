package com.carcompany.web_project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employee_id;

    private String fname;

    private String lname;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "saloon_id")
    private Saloon saloon;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    private User user;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Purchase> purchases;
}
