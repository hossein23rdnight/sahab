package com.sahab.sahab.model;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;

public class LogSender {


    public static void main(String[] args) throws InterruptedException {

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("acks", "all");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer kafkaproducer = new KafkaProducer(properties);


        File directory = new File("log");
        File[] files = directory.listFiles();

       // while (true) {
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        try {
                            Scanner scanner = new Scanner(file);

                            while (scanner.hasNextLine()) {
                                String line = scanner.nextLine();
                                String[] params = line.split("-");


                                if (params.length == 12) {
                                    ProducerRecord producerRecord = new ProducerRecord("mykafka", "name", line);
                                    kafkaproducer.send(producerRecord);
                                }
                            }

                            scanner.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    file.delete();
                    //Thread.sleep(2000);
                }

            }
        //}

         kafkaproducer.close();
    }


}
