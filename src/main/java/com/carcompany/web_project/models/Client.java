package com.carcompany.web_project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "clients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @Column(name = "embg")
    private Long embg;

    private String fname;

    private String lname;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Purchase> purchases;

}
