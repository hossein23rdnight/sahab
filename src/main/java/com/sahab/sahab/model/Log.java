package com.sahab.sahab.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Log {


    private String type;

    private String event;

    private String thread;

    private String info;

    private String year;

    private String month;

    private String day;

    private String hour;

    private String minute;

    private String second;

    private String appName;

    private String msg;


}
