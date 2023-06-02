package com.sahab.sahab.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import java.time.Duration;
import java.time.LocalDateTime;

public class LogicLog {


    URL url = new URL("http://localhost:8080/api/v1/Log/add");
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();


    private ArrayList<Log> logs;

    public LogicLog() throws IOException {
    }

    public void setLogs(ArrayList<Log> logs) {
        this.logs = logs;
    }


    public void check() throws IOException {




        for (Log temp : logs) {

            if (firstRule(temp)) {
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Authorization", "Bearer YOUR_API_TOKEN");
                Warning warning = new Warning();
                warning.setAppname(temp.getAppName());
                warning.setInfo(temp.getInfo());
                warning.setWarnText("bad thing occurred");
                warning.setMsg1(temp.getMsg());
                warning.setMsg2(null);
                warning.setRate(null);

                ObjectMapper objectMapper = new ObjectMapper();
                String requestBody = objectMapper.writeValueAsString(warning);
                DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                outputStream.writeBytes(requestBody);
                outputStream.flush();


            }
        }
        secAndThirdRuleAppleTv();


    }

    private boolean firstRule(Log log) {
        return log.getInfo().equals("error") | log.getInfo().equals("warning") | log.getInfo().equals("unknown error");
    }


    private void secAndThirdRuleAppleTv() throws IOException {
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer YOUR_API_TOKEN");
        ArrayList<Log> appleTv = new ArrayList<>();
        ArrayList<LocalDateTime> appleTvTime = new ArrayList<>();
        int appleTvCount = 0;
        Long appleTvRate;
        for (Log temp : logs) {
            if (temp.getAppName().equals("appleTv")) {
                appleTv.add(temp);
                LocalDateTime dateTime = LocalDateTime.of(Integer.parseInt(temp.getYear()), Integer.parseInt(temp.getMonth()), Integer.parseInt(temp.getDay()), Integer.parseInt(temp.getHour()), Integer.parseInt(temp.getMinute()), Integer.parseInt(temp.getSecond()));
                appleTvTime.add(dateTime);
                appleTvCount++;
            }
        }
        Collections.sort(appleTvTime);
        System.out.println(appleTvTime);
        Duration duration = Duration.between(appleTvTime.get(0), appleTvTime.get(appleTvTime.size() - 1));
        appleTvRate = duration.toSeconds() / appleTvCount;
        if (duration.toMinutes() < 2 && appleTvCount > 20) {

            Warning warning = new Warning();
            warning.setAppname("appleTv");
            warning.setInfo(null);
            warning.setWarnText("second rule warning");
            warning.setMsg1(appleTv.get(appleTvTime.size() - 1).getMsg());
            warning.setMsg2(appleTv.get(appleTvTime.size() - 2).getMsg());
            warning.setRate(String.valueOf(appleTvRate));

            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(warning);
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(requestBody);
            outputStream.flush();

        } else if (appleTvRate > 50) {
            String postData = "{\"text\": \"" + "third rule warning" + "\"}" + appleTvRate;
            connection.setDoOutput(true);
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(postData);
            outputStream.flush();


            Warning warning = new Warning();
            warning.setAppname("appleTv");
            warning.setInfo(null);
            warning.setWarnText("third rule warning");
            warning.setMsg1(null);
            warning.setMsg2(null);
            warning.setRate(String.valueOf(appleTvRate));

            ObjectMapper objectMapper2 = new ObjectMapper();
            String requestBody2 = objectMapper2.writeValueAsString(warning);
            DataOutputStream outputStream2 = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(requestBody2);
            outputStream.flush();

        }


    }


}
