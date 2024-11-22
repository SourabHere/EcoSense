# 🌿 EcoSense

EcoSense is a **microservices-based dashboard application** designed to monitor and analyze environmental and climate-related data. It integrates multiple APIs, such as OpenWeather and OpenAQ, to provide real-time insights into air quality, climate, and biodiversity, enabling informed decision-making for environmental conservation.

---

## 🚀 Features

- **Microservices Architecture**:  
  Decoupled services for modular development, including climate, biodiversity, and pollution data.

- **Spring Cloud Gateway**:  
  Centralized API gateway for routing and service discovery using Eureka.

- **Data Aggregation Service**:  
  Aggregates data from multiple services into cohesive insights, ensuring seamless scalability.

- **Environment API Integration**:  
  - **Climate Service**: Fetches real-time weather data via the OpenWeather API.  
  - **Pollution Service**: Monitors air quality using the OpenAQ API.  
  - **Biodiversity Service**: (Planned) Provides insights into flora and fauna diversity.  

- **Scalable Design**:  
  Modular and scalable architecture for easy addition of new services.

- **Hybrid Deployment Ready**:  
  Optimized for local and cloud environments.

---

## 📂 Project Structure

```plaintext
EcoSense/
├── gateway-service/          # Spring Cloud Gateway for API routing
├── climate-service/          # Microservice for climate data (OpenWeather API)
├── pollution-service/        # Microservice for air pollution data (OpenAQ API)
├── data-aggregation-service/ # Combines data from various services
├── eureka-server/            # Service registry for microservices
└── docs/                     # Documentation and API references
