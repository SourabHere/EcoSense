from fastapi import FastAPI
from app.api import endpoints
from app.services.consumer_service import start_consumer
from py_eureka_client.eureka_client import EurekaClient


app = FastAPI()

eureka_client = EurekaClient(
    eureka_server="http://localhost:8761/eureka/",
    app_name="fastapi-Kafka-Consumer-Service",
    instance_port=8004
)


app.include_router(endpoints.router)

@app.on_event("startup")
async def startup_event():
    await eureka_client.start()
    start_consumer()
