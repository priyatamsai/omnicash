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
    private long Oid;

    @Getter
    @Setter
    private String outletName;

    @Getter
    @Setter
    private long balance_money;

    @OneToOne
    @Getter
    @Setter
    private Location location;
}
