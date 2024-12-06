import paho.mqtt.client as mqtt
import time
import paho.mqtt.client as mqtt
import time
import subprocess
import uuid
import base64

# Generate a unique filename using UUID
unique_id = uuid.uuid4().hex  # Generates a random UUID and converts it to a hexadecimal string
file_name = f"audio_{unique_id}.wav"

# Run the arecord command to record audio with the dynamic filename
subprocess.run(['sudo', 'arecord', '-D', 'plughw:1,0', '-d', '10' ,file_name])

# Callback called when the client connects to the broker
def on_connect(client, userdata, flags, rc):
    if rc == 0:
        print("Connected successfully")
        client.subscribe("test")  # Subscribe to the "test" topic
    else:
        print("Failed to connect, return code: " + str(rc))

# Callback called when a message is received
def on_message(client, userdata, message):
    print(f"Message received on topic '{message.topic}'")
    # Save the received binary data as an audio file
    with open("test.wav", "wb") as audio_file:  # Adjust filename/extension as needed
        audio_file.write(message.payload)
    print("Audio file saved successfully.")

# Callback called when a message is published
def on_publish(client, userdata, mid):
    print(f"Message {mid} published successfully!")

# MQTT client configuration
client = mqtt.Client()
client.on_connect = on_connect
client.on_message = on_message
client.on_publish = on_publish  # Callback to track when publish is done

# Load the audio file directly as binary data
with open(file_name, "rb") as audio_file:
    audio_data = audio_file.read()
    audio_base64 = base64.b64encode(audio_data).decode('utf-8')  # Convertir en texte pour MQTT

# Creer payload avec le nom et les donnees
payload = f"{file_name}|{audio_base64}"

# client.username_pw_set("cit", "cit")  # Set your username and password if needed

# Connect to the MQTT broker (adjust the broker's IP address if necessary)
client.connect("10.0.1.15", 1883, 60)  # Use localhost if RabbitMQ is on the same machine

# Start the MQTT client loop
client.loop_start()

# Publish the binary audio data directly to the "test" topic
result = client.publish("test", payload)
result.wait_for_publish()  # Ensure the message is published before continuing

# Give some time for the message to be sent and received
time.sleep(5)

# Stop the loop and disconnect the client
client.loop_stop()
client.disconnect()
