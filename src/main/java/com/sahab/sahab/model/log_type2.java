package com.sahab.sahab.model;

public class log_type2 extends Log {

    public log_type2(String type, String appName, String year, String month, String day, String hour, String minute, String second, String info , String thread, String event, String msg){
        super.setType(String.valueOf(type));
        super.setEvent(event);
        super.setEvent(thread);
        super.setEvent(info);
        super.setEvent(year);
        super.setEvent(month);
        super.setEvent(day);
        super.setEvent(hour);
        super.setEvent(minute);
        super.setEvent(second);
        super.setEvent(appName);
        super.setEvent(msg);
    }
    public void print() {
        System.out.println(super.getType() + " - " + super.getEvent() + " - " + super.getThread() + " - " + super.getInfo() + " - " + super.getYear() + " - " + super.getMonth() + " - " + super.getDay() + " - " + super.getHour() + " - " + super.getMinute() + " - " + super.getSecond() + " - " + super.getAppName() + " - " + super.getMsg());
    }
}
