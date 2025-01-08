[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/dhH-impT)
[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-2e0aaae1b6195c2367325f4f02e2d04e9abb55f0b24a779b69b11b9e10269abc.svg)](https://classroom.github.com/online_ide?assignment_repo_id=16489304&assignment_repo_type=AssignmentRepo)
# Projet Cloud IoT - INSA Lyon

Dans ce repertoire vous allez enregistrez les démarches que l'équipe de travail a suivi
pour mettre en place une architecture Edge/Fog IoT fonctionnel.

Dans ce fichier `README.md` vous devez décrire en format Markdown chacune des étapes et comptes rendus de chaque avancement, pensez bien à la répartition des tâches dans votre équipe et si besoin créez d'autres repertoires ou utilisez les sous-dossiers proposés.


## Équipe

Notre équipe projet est composée par :

- Samar Chamkhi (Project manager and Storage)
- Longrui Ma (Project manager and Storage)
- Yanjun Ge (Storage)
- Mouahid Balle (Reactive Streaming)
- Nicolas Tordomar (Reactive Streaming)
- Luciano Neimark (Reactive Streaming)
- Sofia Paula Altman Vogl (Reactive Streaming)
- Felipe Mindlin (Reactive Streaming)
- Ewan Talec (Reactive Streaming)
- Sokhna Diata (Sensors and messaging)
- Xinyi Liu (Sensors and messaging)
- Van Quang (Sensors and messaging)
- Alpha Kabinet Cisse (DevOps)
- Yunwei Sun (DevOps)
- Chijin Gui (DevOps)
- Hamza Dihmane (Computing)
- Rafael Ouillon (Computing)
- Leo Pyla (Computing)
- Cristóbal Valenzuela (Computing)




## Description du Projet

### Infrastructure

Le présent projet est deployé dans la plateforme YouPi du Laboratoire CITI en utilisant les noeuds suivants :

```
Rajouter tous les noeuds utilisés
```
Pour se connecter aux noeuds, il faut passer par l'intermediaire du noeud bastion en ssh :

```
youpi.citi-lab.fr
```

Les noeuds sont à réserver sur le site web de la plateforme.


### Cas d'utilisation


## Répartition des tâches
Lors de ce projet les tâches ont été réparties comme suit :

- Sensors and messaging Team
- Reactive Streaming Team
- Storage Team
- Computing Team
- Deployment Team 

### Suivi journalier

**Vendredi 04/10/2024** :
- Introduction and presentation of Cloud Iot project
- Assignment of groups  
- Creation of a global Trello board for project management
- Creation of a Discord server with channels for communication
- Decision-making on the topic of our project

**Vendredi 11/10/2024** :
- Sensors and messaging team set up an MQTT network using Mosquitto, starting with the creation of a Youpi account, though they encountered some initial issues connecting the Raspberry Pi due to SD card problems. After resolving these, they successfully installed Mosquitto on the Raspberry Pi and began configuring it for client/server communication. Additionally, they connected a microphone and sound card to the Raspberry Pi to integrate sensor capabilities into the setup
- Computing team successfully ran audio transcription models for both French and English locally on a PC, achieving a transcription speed of 0.7 seconds for 5-second audio clips. They also explored methods to handle sliced audio segments to prevent word loss during transcription, discussing the potential use of Spark to leverage greater computing power for improved performance.
- Storage team studied the structure of databases and proposed two schema designs for distributed data storage using MongoDB, aiming to optimize data management and scalability.
- Deployment team successfully deployed the Mosquitto Docker image and successfully established communication between two local terminals using Docker. However, attempts to run the setup between two distant computers were unsuccessful, highlighting the need for further troubleshooting.
- Reactive Streaming Team decided to set up a Kafka instance to facilitate communication between multiple PCs and successfully established messaging between three PCs acting as a queue, producer, and consumer using a personal hotspot. However, they faced a setback as they were unable to connect through the faculty Wi-Fi (Eduroam). 

**Mardi 22/10/2024** :
- Sensors and messaging team have implemented a publisher and two subscribers. Theu, also succeeded to record and publish an audio file on one node. 
- Reactive Streaming team configured Kafka for multiple consumers.
- Computing team started working on spark investigation and Testing models on Raspberry Pi 3B
- Storage team did a local deployment of MangoDB
- Deployment team tested MangDB, Spark and kafka with Docker images.
  
**Mercredi 13/11/2024** :
- Sensors and messaging team configured a cluster with one publisher and two subscribers, corrected audio file handling using alsamixer, and updated the code to include IDs for identifying audio files sent to subscribers.
- Reactive Streaming team implemented brokers but encountered an issue when terminating an instance. In the next sprint, they plan to resolve this issue and begin developing an MQTT messaging interface.
- Computing team successfully ran a Spark master and a worker on separate Raspberry Pis but encountered issues with communication between them and executing jobs across the setup.
- Storage team  began setting up a replica set and sharding to enhance database scalability and reliability.
- Deployment team attempted to build the image using the file provided on GitHub for the group message server (Mosquitto).

**Vendredi 06/12/2024** :
- sensors and messaging team updated the file naming format to include timestamps (e.g., audio_20241206_135712.wav), enabled continuous audio transmission every 4 seconds until interrupted, and implemented an MQTT Broker Bridge with a local broker and two remote brokers, allowing subscribers to receive files from multiple sources.
- Reactive Streaming team resolved the previous bug, completed the MQTT interface, and established a connection with the Computing team. They aim to connect with the Sensor team by the next class.
- Computing team implemented the ability to translate transcribed messages and worked on dockerizing the current code for easier deployment and maintenance on Raspberry Pis. The Docker setup successfully enabled running Spark and communication between the master and worker. The team collaborated with the Messaging team to set up a consumer that receives and transcribes audio files.
- Storage team is continuing the deployment of the replica set and sharded cluster to ensure robust and scalable database architecture.
- Deployment team created and tested a Mosquitto image locally (publisher and subscriber) with 3 containers. The next step is to test the image with 2 Raspberry Pis.

  **Mercredi 18/12/2024** :
- collaborated with the Docker team to create an image, successfully built despite a sudo-related bug in the recording command that is under debugging. They also implemented MQTT QoS1, confirmed PUBACK reception, and verified reliable delivery between the publisher and the MQTT server. The team is evaluating the optimal size for audio segments, currently set to 4 seconds, in consultation with the Computing team.
- Reactive Streaming team ??
- Computing team ?? 
- Storage team confirmed Docker images are functioning correctly and is currently building a Self-Managed Replica Set and Sharded Cluster (5 servers, 3 sharded clusters). The next steps include creating a DB template and connecting to the cloud.
- Deployment ?? 

**Mercredi 08/01/2025** :
- 
- 
- 
- 

- 
**Soutenance 15/01/2025** :
- 
- 
- 
- 
- 
- 

## Procédure de mise en place de votre architecture Cloud IoT


## Conclusions et recommandations
