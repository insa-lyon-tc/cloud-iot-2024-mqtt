import paho.mqtt.client as mqtt

# Callback for when the client connects to the broker
def on_connect(client, userdata, flags, rc):
    if rc == 0:
        print("Connected successfully")
        client.subscribe("test")  # Subscribe to the "test" topic
    else:
        print("Failed to connect, return code: " + str(rc))

# Callback for when a message is received
def on_message(client, userdata, message):
    print(f"Message received on topic '{message.topic}'")
    with open("received_audio.wav", "wb") as audio_file:  # Save the audio file
        audio_file.write(message.payload)
    print("Audio file saved successfully.")

client = mqtt.Client()
client.on_connect = on_connect
client.on_message = on_message

# Connect to the broker
client.connect("10.0.1.15", 1883, 60)

# Start the MQTT loop
client.loop_forever()
