package com.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import java.io.*;

public class MQTTClient {
    public static void main(String[] args) {
        String broker = "tcp://localhost:1883"; // Change if needed
        String clientId = "JavaClient";
        MqttClient mqttClient = null;

        try {
            mqttClient = new MqttClient(broker, clientId);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            mqttClient.connect(connOpts);

            // Read the WAV file as a byte array (raw binary data)
            byte[] fileBytes = readFile("/Users/luciano/Downloads/melody_500KB.wav");

            // Publish the raw binary data
            MqttMessage message = new MqttMessage(fileBytes);
            mqttClient.publish("test/topic", message);

            mqttClient.disconnect();
        } catch (MqttException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (mqttClient != null && mqttClient.isConnected()) {
                    mqttClient.disconnect();
                }
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    // Helper function to read file into a byte array
    public static byte[] readFile(String filePath) throws IOException {
        File file = new File(filePath);
        byte[] fileBytes = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(fileBytes);
        }
        return fileBytes;
    }
}