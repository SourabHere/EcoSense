from kafka import KafkaProducer
import json
from ..config import Config

class KafkaService:
    def __init__(self):
        self.producer = KafkaProducer(
            bootstrap_servers=Config.KAFKA_BROKER,
            value_serializer=lambda v: json.dumps(v).encode('utf-8')
        )

    def send_message(self, topic: str, message: dict):
        self.producer.send(topic, message)
        self.producer.flush()
