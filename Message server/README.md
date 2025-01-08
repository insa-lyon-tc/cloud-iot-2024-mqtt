# CIT

# Intruction to enable Mosquitto MQTT server

## Step 1: Install package required
  ```bash
  sudo apt update 
  sudo apt-get install python3-pip 
  pip3 install paho-mqtt
  sudo apt install mosquitto mosquitto-clients
  ````

## Step 2 :Create configuation file
   ```bash 
   sudo nano /etc/mosquitto/mosquitto.conf
   ```
  Add 2 line to mosquitto.conf
   ```bash
  listener 1883 0.0.0.0  
  allow_anonymous true
   ```
## Step 3: Start and Enable Mosquito server
  ```bash
  sudo systemctl start mosquitto
  sudo systemctl enable mosquitto
  sudo systemctl status mosquitto # check status server Mosquitto
  ```
## Step 4: Finally


### Testing the MQTT Server  
- Download **Subscriber.py** from the message server.  
- Update the broker IP address in **Subscriber.py** to match the desired topic source.  
