package com.example.omnicash.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long uid;

    @Getter
    @Setter
    @NotNull
    private String userName;

    @Getter
    @Setter
    private Long money_wallet;

    @Getter
    @Setter
    private String active_otp;

    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date OtpcreatedAt;

    @Getter
    @Setter
    @NotBlank
    private String Contact;


}
