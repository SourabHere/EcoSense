from sqlalchemy.orm import Session
from ..models.model import ModelMetadata

class ModelFetcherService:
    def __init__(self, db: Session):
        self.db = db

    def get_model_by_id(self, model_id: str):
        model = self.db.query(ModelMetadata).filter(ModelMetadata.model_id == model_id).first()
        if not model:
            raise ValueError("Model not found")
        return model
