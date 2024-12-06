package com.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class MosquittoToKafkaBridge {

    public static void main(String[] args) throws Exception {
        String mqttBroker = "tcp://localhost:1883";
        String mqttTopic = "test/topic";
        String mqttClientId = "MosquittoToKafkaBridge";

        String kafkaBroker = "localhost:9092";
        String kafkaTopic = "topic-m4";

        Properties kafkaProps = new Properties();
        kafkaProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBroker);
        kafkaProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(kafkaProps);

        MqttClient mqttClient = new MqttClient(mqttBroker, mqttClientId);
        mqttClient.connect();

        mqttClient.subscribe(mqttTopic, (topic, message) -> {
            String payload = new String(message.getPayload());
            System.out.println("Received MQTT message: " + payload);

            ProducerRecord<String, String> record = new ProducerRecord<>(kafkaTopic, payload);
            kafkaProducer.send(record, (metadata, exception) -> {
                if (exception == null) {
                    System.out.println("Message forwarded to Kafka topic: " + metadata.topic());
                } else {
                    exception.printStackTrace();
                }
            });
        });

        System.out.println("Bridge is running. Press Ctrl+C to stop.");
        Thread.currentThread().join();

        mqttClient.disconnect();
        kafkaProducer.close();
    }
}
