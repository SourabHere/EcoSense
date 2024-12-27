import os

class Config:
    DATABASE_URL = os.getenv("DATABASE_URL", "mysql+pymysql://root:Sourab2002@localhost:3306/EcoSense")
    KAFKA_BROKER = os.getenv("KAFKA_BROKER", "localhost:9092")
    KAFKA_TOPIC = os.getenv("KAFKA_TOPIC", "ecoSense")
    ENVIRONMENT = os.getenv("ENVIRONMENT", "development")


