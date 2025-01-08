import paho.mqtt.client as mqtt
import base64

def on_connect(client, userdata, flags, rc):
    print("Connected to broker")
    client.subscribe("test/#", qos=1)

def on_message(client, userdata, message):
    # Décoder le payload pour récupérer le nom et les données audio
    payload = message.payload.decode('utf-8')
    file_name, audio_base64 = payload.split("|", 1)

    # Convertir les données audio de base64 en binaire
    audio_data = base64.b64decode(audio_base64)

    # Sauvegarder l’audio avec le nom du fichier reçu
    with open(file_name, "wb") as audio_file:
        audio_file.write(audio_data)
    print(f"Audio reçu et enregistré sous le nom : {file_name}")

client = mqtt.Client()
client.on_connect = on_connect
client.on_message = on_message
client.connect("10.0.1.3", 1883, 60) #Choose the IP broker you want (10.0.1.3 / 10.0.1.6)

client.loop_forever()
