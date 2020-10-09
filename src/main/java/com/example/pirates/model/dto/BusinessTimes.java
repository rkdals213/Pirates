package com.example.pirates.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.sql.Date;
import java.sql.Time;

@Entity
@Getter
@Setter
//@NamedEntityGraph(name = "BusinessTimesWithStore", attributeNodes = @NamedAttributeNode("store"))
public class BusinessTimes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String day;
    String open;
    String close;

    @ManyToOne
    @JoinColumn(name = "store_id")
    @JsonBackReference
    Store store;

    @Override
    public String toString() {
        return "BusinessTimes{" +
                "id=" + id +
                ", day='" + day + '\'' +
                ", open='" + open + '\'' +
                ", close='" + close + '\'' +
                '}';
    }
}
