package com.sahab.sahab.model;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class LogReceiver {
    public static void main(String[] args) throws IOException {
        ArrayList<Log> logs = new ArrayList<>();
        LogicLog logicLog = new LogicLog();


        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", "group1");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", StringDeserializer.class.getName());
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList("mykafka"));
        // while (true) {
        ConsumerRecords<String, String> records = consumer.poll(100);
        for (ConsumerRecord<String, String> record : records) {
            System.out.println("====================");
            System.out.println(record.value());
            System.out.println("====================");

            String temp = String.valueOf(record.value());
            String[] params = temp.split("-");

            if (params[0].equals("1")) {
                log_type1 log_type1 = new log_type1(params[0], params[1], params[2], params[3], params[4], params[5], params[6], params[7], params[8], params[9], params[10], params[11]);
                logs.add(log_type1);


            } else {
                log_type2 log_type2 = new log_type2(params[0], params[1], params[2], params[3], params[4], params[5], params[6], params[7], params[8], params[9], params[10], params[11]);
                logs.add(log_type2);

            }

        }
        logicLog.setLogs(logs);
        logicLog.check();
        logs.clear();

        //}


    }
}
