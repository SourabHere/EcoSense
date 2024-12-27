from fastapi import APIRouter, HTTPException, Depends
from sqlalchemy.orm import Session
from ..services.kafkaService import KafkaService
from ..services.prediction_service import PredictionService
from ..services.modelServices import ModelFetcherService
from ..database import get_db

router = APIRouter()

kafka_service = KafkaService()

@router.post("/api/v1/predict")
def create_prediction(payload: dict):
    try:
        prediction_service = PredictionService(kafka_service)
        return prediction_service.process_prediction_request(payload)
    except ValueError as e:
        raise HTTPException(status_code=400, detail=str(e))
    
@router.get("/api/v1/health")
def create_prediction():
    return {
        "message": "Server is UP"
    }

@router.get("/model/{model_id}")
def fetch_model(model_id: str, db: Session = Depends(get_db)):
    model_fetcher = ModelFetcherService(db)
    try:
        model = model_fetcher.get_model_by_id(model_id)
        return model
    except ValueError as e:
        raise HTTPException(status_code=404, detail=str(e))
