package com.example.omnicash.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long uid;

    @Getter
    @Setter
    private String userName;

    @Getter
    @Setter
    private long money_wallet;

    @Getter
    @Setter
    private String active_otp;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date createdAt = new Date();


}
