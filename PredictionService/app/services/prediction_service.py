from datetime import datetime

from app.repositories.train_data_repository import register_data
from .kafkaService import KafkaService

class PredictionService:
    def __init__(self, kafka_service: KafkaService):
        self.kafka_service = kafka_service

    def process_prediction_request(self, payload: dict):

        current_date = datetime.now()
        formatted_date = current_date.strftime("%d/%m/%Y")
        payload["requestDate"] = formatted_date

        train_data = {
            "climate": payload["climate"],
            "biodiversity": payload["biodiversity"]
        }

        purpose = payload["purpose"]

        formatted_data = {
            "purpose" : purpose,
            "trainData" : train_data,
            "startDate" : payload["startDate"],
            "endDate" : payload["endDate"],
            "requestDate" : payload["requestDate"],
            "location" : payload["location"]
        }

        formatted_data["trainData"] = train_data

        train_record = register_data(formatted_data)

        formatted_data["id"] = train_record.id


        self.kafka_service.send_message("training_requests", formatted_data)


        return {"message": "Request submitted successfully!"}
