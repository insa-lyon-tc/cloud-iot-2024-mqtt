package com.monprojet;

import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class KafkaConfig {
    private String kafkaIP;
    private String kafkaPort;

    public void loadKafkaConfig() {
        try {
            // Get the resource as a stream
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("kafkaProperties.json");

            if (inputStream == null) {
                throw new RuntimeException("Could not find the resource: kafkaProperties.json");
            }

            // Use a Scanner to read the InputStream as a String
            Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name());
            String content = scanner.useDelimiter("\\A").next();
            scanner.close();

            // Parse the JSON content
            JSONObject json = new JSONObject(content);
            this.kafkaIP = json.getString("kafkaIP");
            this.kafkaPort = json.getString("kafkaPort");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getKafkaIP() {
        return kafkaIP;
    }

    public String getKafkaPort() {
        return kafkaPort;
    }
}
