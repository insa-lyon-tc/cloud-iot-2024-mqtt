package com.monprojet;

import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class KafkaConfig {
    private String kafkaIP;
    private String kafkaPort;

    public void loadKafkaConfig() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("/Users/luciano/Desktop/cloud-iot-2024-mqtt/Reactive Stream/mon-projet-kafka/src/main/kafkaProperties.json")));
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
