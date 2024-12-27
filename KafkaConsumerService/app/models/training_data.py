from sqlalchemy import Column, Integer, String, JSON
from database import engine, SessionLocal, Base


class TrainingData(Base):
    __tablename__ = "train_data"
    
    id = Column(Integer, primary_key=True, index=True)
    location = Column(String(255), nullable=False)
    train_data = Column(JSON, nullable=False)
    status = Column(String(255), default='queued') 
    start_date = Column(String(255))
    end_date = Column(String(255))
    request_date = Column(String(255))
    



Base.metadata.create_all(bind=engine)
