from sqlalchemy.orm import Session
from database import SessionLocal

from app.models.training_data import TrainingData  

def update_file_status(db: Session, train_id: int, status: str):
    file_record = db.query(TrainingData).filter(TrainingData.id == train_id).first()
    file_record.status = status
    # file_record.predictions_data = predictions_data
    db.commit()
    db.refresh(file_record)
    return file_record

def update_file_status_with_data(db: Session, train_id: int, status: str, train_data: dict):
    file_record = db.query(TrainingData).filter(TrainingData.id == train_id).first()
    file_record.status = status
    file_record.train_data = train_data
    db.commit()
    db.refresh(file_record)
    return file_record