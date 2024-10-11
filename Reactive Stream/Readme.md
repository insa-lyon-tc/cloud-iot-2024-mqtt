# Real-Time Kafka Messaging

## Description
Ce projet permet d'envoyer et de recevoir des messages en temps réel en utilisant **Apache Kafka** avec un **Producer** et un **Consumer** écrits en Java. L'utilisateur peut saisir des messages dans la console du Producer, et ceux-ci seront instantanément reçus et affichés par le Consumer.

## Prérequis
- **Java 8** ou version supérieure
- **Apache Kafka** et **Zookeeper** installés et configurés
- **Maven** pour la gestion des dépendances

## Installation et Configuration

1. **Téléchargez et installez Apache Kafka** :
   - Téléchargez Kafka depuis le site officiel : [https://kafka.apache.org/downloads](https://kafka.apache.org/downloads)
   - Extrayez les fichiers dans un dossier de votre choix (par exemple, `C:\kafka`).

2. **Démarrez Zookeeper et Kafka** :
   Ouvrez deux terminaux et exécutez les commandes suivantes :

   - Terminal 1 : Démarrer Zookeeper
     ```bash
     cd C:\kafka
     .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
     ```

   - Terminal 2 : Démarrer Kafka
     ```bash
     cd C:\kafka
     .\bin\windows\kafka-server-start.bat .\config\server.properties
     ```

3. **Créez un topic Kafka** :
   - Créez un topic nommé `mon_topic` :
     ```bash
     .\bin\windows\kafka-topics.bat --create --topic mon_topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
     ```

## Cloner le Projet et Configurer Maven
1. Clonez le projet depuis le dépôt GitHub :
   ```bash
   git clone https://github.com/votre-utilisateur/real-time-kafka-messaging.git
   cd real-time-kafka-messaging
