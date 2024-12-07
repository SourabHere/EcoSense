from datetime import datetime
from pymongo import MongoClient

client = MongoClient("mongodb://localhost:27017/")
db = client['model_db']

def save_training_metadata(model_name, start_date, end_date, model_path, report):
    """
    Save training metadata to the database.
    """
    collection = db['model_metadata']
    collection.insert_one({
        "model_name": model_name,
        "start_date": start_date,
        "end_date": end_date,
        "model_path": model_path,
        "report": report,
        "created_at": datetime.now()
    })
