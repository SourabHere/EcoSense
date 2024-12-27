from sqlalchemy import Column, Integer, String, JSON, DateTime
from ..database import Base

class ModelMetadata(Base):
    __tablename__ = "models"

    id = Column(Integer, primary_key=True, index=True)
    name = Column(String(100), nullable=False)
    start_date = Column(String(200), nullable=False)
    end_date = Column(String(200), nullable=False)
    model_id = Column(String(200), unique=True, nullable=False)
    report = Column(JSON, nullable=False) 
    created_at = Column(DateTime, nullable=False)
