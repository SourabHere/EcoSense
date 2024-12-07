import pickle

def load_model(model_path="./models/trained_model.pkl"):
    """
    Load a pre-trained ML model from disk.
    """
    with open(model_path, "rb") as file:
        model = pickle.load(file)
    return model
