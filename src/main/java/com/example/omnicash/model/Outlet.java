package com.example.omnicash.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "outlet")
public class Outlet {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long Oid;

    @Getter
    @Setter
    private String outletName;

    @Getter
    @Setter
    private Boolean isActive;

    @Getter
    @Setter
    private Long balance_money;

    @Getter
    @Setter
    private String city_name;

    @Getter
    @Setter
    private Double latitude;

    @Getter
    @Setter
    private Double longitude;
    
    
}
