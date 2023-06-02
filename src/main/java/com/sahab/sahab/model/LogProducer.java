package com.sahab.sahab.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


public class LogProducer {
    public static void main(String[] args) throws InterruptedException {


        System.out.println("enter your log type");
        System.out.println("1 -> event - thread - info - time - appName - msg");
        System.out.println("2 -> appName - time - info - thread - event - msg");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();


        String[] appName = {"appleTv", "Applemusic", "Telegram", "ToggleTrack"};
        String[] eventLogs = {"network traffic usage", "failed password attempts"};
        String[] infoLevels = {"error", "warning", "start install", "unknown error"};
        String[] messages = {"this is the test message", "hello", "help"};

        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            String name = appName[random.nextInt(appName.length)];
            int Year = random.nextInt(4) + 2020;
            int month = random.nextInt(12) + 1;
            int day = random.nextInt(31) + 1;
            int time = random.nextInt(24) + 1;
            int min = random.nextInt(60);
            int sec = random.nextInt(60);


            String filename = name + "-" + Year + "-" + month + "-" + day + "-" + time + "-" + min + "-" + sec + ".log";
            String filePath = "log/" + filename;

            try {
                File file = new File(filePath);
                file.getParentFile().mkdirs();
                FileWriter writer = new FileWriter(file);

                for (int j = 0; j < random.nextInt(1, 6); j++) {
                    String eventLog = eventLogs[random.nextInt(eventLogs.length)];
                    int logtime = random.nextInt(24) + 1;
                    int logmin = random.nextInt(60);
                    int logsec = random.nextInt(60);
                    int thread = random.nextInt(100);
                    String infoLevel = infoLevels[random.nextInt(infoLevels.length)];
                    String message = messages[random.nextInt(messages.length)];

                    if (choice == 1) {
                        String logSentence = "\n1-" + eventLog + "-" + thread + "-" + infoLevel + "-" + Year + "-" + month + "-" + day + "-" + logtime + "-" + logmin + "-" + logsec + "-" + name + "-" + message;
                        writer.write(logSentence);


                    } else {
                        String logSentence = "\n2-" + name + "-" + Year + "-" + month + "-" + day + "-" + logtime + "-" + logmin + "-" + logsec + "-" + infoLevel + "-" + thread + "-" + eventLog + "-" + message;
                        writer.write(logSentence);

                    }
                }


                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            //Thread.sleep(2000);
        }

    }
}
