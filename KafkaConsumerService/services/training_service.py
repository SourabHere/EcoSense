import pandas as pd
from sklearn.linear_model import LinearRegression
from app.services.storage_service import save_model
from app.services.report_service import generate_report
from app.repositories.database_repository import save_training_metadata

def train_model(model_name, start_date, end_date, data_source):
    """
    Trains a model and saves it along with its report.
    """
    # Load training data
    data = pd.read_csv(data_source)  # Replace with your data fetching logic

    # Preprocessing (placeholder)
    X = data[['feature1', 'feature2']]  # Features
    y = data['target']                 # Target

    # Train model
    model = LinearRegression()
    model.fit(X, y)

    # Save model
    model_path = save_model(model, model_name, start_date, end_date)

    # Generate report
    report = generate_report(model, X, y)

    # Store metadata and report in the database
    save_training_metadata(
        model_name=model_name,
        start_date=start_date,
        end_date=end_date,
        model_path=model_path,
        report=report
    )
