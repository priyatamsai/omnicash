package com.example.omnicash.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "locations")
public class Location {

    @Id
    @Getter
    @Setter
    private long Lid;

    @Getter
    @Setter
    private String city_name;

    @Getter
    @Setter
    private String latitude;

    @Getter
    @Setter
    private String longitude;
}
