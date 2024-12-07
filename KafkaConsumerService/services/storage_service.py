import os
import pickle
from datetime import datetime

def save_model(model, model_name, start_date, end_date, storage_path="./models"):
    """
    Save the trained model with a standardized naming convention.
    """
    timestamp = datetime.now().strftime("%Y%m%d%H%M%S")
    filename = f"{model_name}_{start_date}_{end_date}_{timestamp}.pkl"

    os.makedirs(storage_path, exist_ok=True)
    file_path = os.path.join(storage_path, filename)

    with open(file_path, "wb") as file:
        pickle.dump(model, file)

    # Optionally upload to cloud storage (e.g., S3, Azure)
    # Example: upload_to_s3(file_path, bucket_name)

    return file_path
