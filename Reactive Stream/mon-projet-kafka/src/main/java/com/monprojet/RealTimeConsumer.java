package com.monprojet;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.UUID;
import java.util.Collections;
import java.util.Properties;

public class RealTimeConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();

        KafkaConfig config = new KafkaConfig();
        config.loadKafkaConfig();
        String kafkaIP = config.getKafkaIP();
        String kafkaPort = config.getKafkaPort();
        String kafkaAddress = kafkaIP + ":" + kafkaPort;

        props.put("bootstrap.servers", kafkaAddress);
        props.put("group.id", "groupe_unique_" + UUID.randomUUID()); // Chaque consommateur a un group.id unique
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");


        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("mon_topic"));

        System.out.println("En attente de messages...");

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("Message reçu : key = %s, value = %s%n", record.key(), record.value());
            }
        }
    }
}