package com.monprojet;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Scanner;

public class RealTimeProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez les messages à envoyer à Kafka (tapez 'exit' pour quitter) :");

        while (true) {
            String message = scanner.nextLine();

            // Arrête le producer si "exit" est tapé
            if ("exit".equalsIgnoreCase(message)) {
                break;
            }

            // Envoi du message à Kafka
            ProducerRecord<String, String> record = new ProducerRecord<>("mon_topic", message);
            producer.send(record);
            System.out.println("Message envoyé : " + message);
        }

        producer.close();
        scanner.close();
        System.out.println("Producer arrêté.");
    }
}
