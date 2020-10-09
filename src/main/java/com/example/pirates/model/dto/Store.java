package com.example.pirates.model.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
//@NamedEntityGraph(name = "StoreWithbusinessTimes", attributeNodes = @NamedAttributeNode("businessTimes"))
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String owner;
    private String description;
    private int level;
    private String address;
    private String phone;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<BusinessTimes> businessTimes = new ArrayList<>();

    @ElementCollection
    public List<String> holidays;

}
