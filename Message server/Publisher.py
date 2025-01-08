import paho.mqtt.client as mqtt
import subprocess
import base64
import time
from datetime import datetime
import pytz
import os
import glob
import logging
# MQTT broker address
BROKER_ADDRESS = "10.0.1.15"
TOPIC = "test"

# Configuration du logger
logging.basicConfig(level=logging.DEBUG, format='%(asctime)s - %(levelname)s - %(message)s')

# Specify the desired time zone
timezone = pytz.timezone("Europe/Paris")

# MQTT Callbacks
def on_connect(client, userdata, flags, rc):
    if rc == 0:
        print("Connected successfully")
    else:
        print(f"Failed to connect, return code: {rc}")

def on_publish(client, userdata, mid):
    print(f"PUBACK received for Message ID: {mid}")

# Function to record audio
def record_audio():
    current_time = datetime.now(timezone).strftime("%Y%m%d_%H%M%S")
    file_name = f"audio_{current_time}.wav"
    subprocess.run(['sudo', 'arecord', '-D', 'plughw:1,0', '-d', '4', file_name])
    return file_name

# Function to encode audio to base64
def encode_audio(file_name):
    with open(file_name, "rb") as audio_file:
        audio_data = audio_file.read()
    return base64.b64encode(audio_data).decode('utf-8')

# Initialize MQTT client
client = mqtt.Client()
client.on_connect = on_connect
client.on_publish = on_publish

# Activer le logger MQTT
client.enable_logger(logging.getLogger())

# Connect to the broker
client.connect(BROKER_ADDRESS, 1883, 60)
client.loop_start()

# Continuous loop for recording and publishing
try:
    while True:
        print("Recording audio...")
        file_name = record_audio()  # Record a new audio
        audio_base64 = encode_audio(file_name)  # Encode audio as base64
        
        # Create payload and publish to broker
        payload = f"{file_name}|{audio_base64}"
        result=client.publish(TOPIC, payload, qos=1)  # Non-retained message

        print(f"Published {file_name} to topic '{TOPIC}'with MID {result.mid}")

        # Wait for the next recording
except KeyboardInterrupt:
    print("Stopping...")
finally:
#Supprimer tous les fichiers .wav dans le dossier actuel
    for file in glob.glob("*.wav"):
        os.remove(file)
        print(f"Deleted file: {file}")
    client.loop_stop()
    client.disconnect()
