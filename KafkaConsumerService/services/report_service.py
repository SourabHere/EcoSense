from sklearn.metrics import mean_squared_error, r2_score

def generate_report(model, X, y):
    """
    Generate evaluation metrics for the trained model.
    """
    predictions = model.predict(X)

    mse = mean_squared_error(y, predictions)
    r2 = r2_score(y, predictions)

    return {
        "mean_squared_error": mse,
        "r2_score": r2,
        "coefficients": model.coef_.tolist(),
        "intercept": model.intercept_,
    }
