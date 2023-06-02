package com.sahab.sahab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Warning {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String Appname;

    private String warnText;
    private String rate;
    private String info;
    private String msg1;
    private String msg2;


}