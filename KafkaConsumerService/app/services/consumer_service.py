import asyncio
import json
from aiokafka import AIOKafkaConsumer
from app.configs.config import KAFKA_BROKER_URL, KAFKA_TOPIC, GROUP_ID
from app.services.data_processor import process_message
from datetime import datetime

async def consume():
    consumer = AIOKafkaConsumer(
        KAFKA_TOPIC,
        bootstrap_servers=KAFKA_BROKER_URL,
        group_id=GROUP_ID,
        value_deserializer=lambda x: json.loads(x.decode('utf-8')) 
    )
    await consumer.start()
    try:
        async for message in consumer:
            
            json_data = message.value
            
            id = json_data["id"]
            purpose = json_data["purpose"]

            await process_message(id, json_data)

            print("Completed for id", id)


    finally:
        await consumer.stop()

def start_consumer():
    asyncio.create_task(consume())
