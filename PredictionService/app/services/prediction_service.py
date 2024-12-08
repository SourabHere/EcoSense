from .kafkaService import KafkaService

class PredictionService:
    def __init__(self, kafka_service: KafkaService):
        self.kafka_service = kafka_service

    def process_prediction_request(self, payload: dict):
        self.kafka_service.send_message("training_requests", payload)
        return {"message": "Request submitted successfully!"}
