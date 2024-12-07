from kafka import KafkaConsumer
from json import loads
from app.services.training_service import train_model

consumer = KafkaConsumer(
    'training_topic',
    bootstrap_servers=['localhost:9092'],
    group_id='model_training_group',
    value_deserializer=lambda x: loads(x.decode('utf-8'))
)

for message in consumer:
    request = message.value
    train_model(
        model_name=request["model_name"],
        start_date=request["start_date"],
        end_date=request["end_date"],
        data_source=request["data_source"]
    )
