from fastapi import FastAPI
from .routes import prediction_route
from .models.database import Base, engine
from py_eureka_client.eureka_client import EurekaClient


Base.metadata.create_all(bind=engine)

app = FastAPI()

eureka_client = EurekaClient(
    eureka_server="http://localhost:8761/eureka/",
    app_name="fastapi-ML-microservice",
    instance_port=8005
)

@app.on_event("startup")
async def start_eureka():
    await eureka_client.start()

app.include_router(prediction_route.router, prefix="/api/v1", tags=["Prediction"])



if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8005)