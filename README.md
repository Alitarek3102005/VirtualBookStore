# 📚 Virtual Book Store - Cloud-Native Microservices Architecture

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white)
![AWS](https://img.shields.io/badge/AWS-232F3E?style=for-the-badge&logo=amazon-aws&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Stripe](https://img.shields.io/badge/Stripe-626CD9?style=for-the-badge&logo=Stripe&logoColor=white)

A comprehensive, distributed e-commerce backend built with Spring Boot and Spring Cloud. This project handles the full lifecycle of an online bookstore, deployed to AWS EC2 using Docker Compose with a managed Supabase PostgreSQL data layer.

👉 **[Frontend Repository Here](https://github.com/Alitarek3102005/VirtualBookStoreFrontend.git)**

## 🏗️ System Architecture

Our backend is decomposed into 7 distinct microservices communicating via OpenFeign and routed through a central API Gateway:

*   **API Gateway & Eureka Discovery Server:** Centralized routing and dynamic service registry.
*   **Auth Service:** Secures endpoints and manages user identities and roles.
*   **Catalog Service:** Manages book inventory, categories, and dynamic stock levels.
*   **Cart Service:** Handles stateful shopping cart sessions for users.
*   **Order Service:** Orchestrates cross-service transactions to process checkouts.
*   **Payment Service:** Integrates with the **Stripe API** for secure checkout processing.
*   **Review Service:** Allows users to rate and review their purchased books.

## 🚀 Cloud Deployment (AWS)

This architecture was migrated from local development to a cloud-native environment:
*   **Compute:** Amazon EC2 instance hosting all microservices via Docker Compose.
*   **Database:** Supabase Managed PostgreSQL.
*   **Networking:** Configured AWS VPC Security Groups and solved split-horizon DNS routing to ensure seamless public internet gateway communication.

## 🤝 The Engineering Team

This distributed system was built collaboratively by:
*   **Ali Tarek:** Infrastructure (Docker, AWS), API Gateway, Eureka Server, Catalog Service, Database Configuration
*   **Abdullah Adham:** Auth Service
*   **Amr Soliman:** Cart Service
*   **Ali Hassan:** Order Service
*   **Ahmed Ouisues:** Payment Service (Stripe Integration)
*   **Omar Gaber:** Review Service

## ⚙️ How to Run Locally

1. Clone the repository: `git clone https://github.com/Alitarek3102005/VirtualBookStore.git`
2. Configure your environment variables in the `.env` file (Database credentials, Stripe API keys).
3. Run the Docker cluster:
   ```bash
   docker compose up -d --build
