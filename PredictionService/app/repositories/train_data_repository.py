from sqlalchemy.orm import Session
from app.database import get_db, SessionLocal
from app.models.train_data import TrainingData  

def register_data(data, db = SessionLocal()):
    new_train_record = TrainingData(
        location = data["location"],
        train_data = None,
        start_date = data["startDate"],
        end_date = data["endDate"],
        request_date = data["requestDate"],
        purpose = data["purpose"]
    )
    
    db.add(new_train_record)
    
    db.commit()
    
    db.refresh(new_train_record)
    
    return new_train_record

