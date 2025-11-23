
# ☁️ SmartTicket: Agentic AI Customer Support Platform

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![React](https://img.shields.io/badge/React-18-61DAFB?style=for-the-badge&logo=react&logoColor=black)
![Groq AI](https://img.shields.io/badge/AI-Groq%20Llama3-f55036?style=for-the-badge)
![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?style=for-the-badge&logo=docker&logoColor=white)

> **🔴 Live Demo:** https://smart-ticket-frontend.onrender.com/
---

## 📖 Overview

**SmartTicket** is a multi-tenant CRM ticketing solution designed to mimic the architecture of **Salesforce Agentforce**.

Unlike traditional support systems that simply store data, SmartTicket utilizes an **Agentic AI Logic Layer (Llama 3.3)** to autonomously analyze, prioritize, and draft responses for incoming cases. The system is engineered to prioritize **Trust** and **System Reliability**, ensuring that critical infrastructure failures are flagged immediately while standard inquiries are routed efficiently.

---

## 🚀 Key Features 

### 🤖 1. Agentic Triage & Sentiment Analysis
* **The Problem:** Support teams are overwhelmed by volume and cannot manually sort critical issues fast enough.
* **The Solution:** An integrated AI Agent intercepts every ticket to analyze sentiment and urgency in real-time.
* **The Result:** Automatic enforcement of **SLAs** (Service Level Agreements). "Critical" issues (e.g., Server Crashes) are instantly flagged as **HIGH PRIORITY**.

### 🛡️ 2. Human-in-the-Loop Architecture (Trust Layer)
* Following the principles of the **Einstein Trust Layer**, the AI does not auto-reply to customers.
* Instead, it generates a **Suggested Response** for the human agent to review.
* This ensures data accuracy and maintains the "Human element" in customer relationships.

### 📦 3. Bulkification & Scalability
* The Backend REST APIs are designed to handle high-volume traffic.
* Implemented **Bulk Patterns** in the Service Layer to process lists of records efficiently, minimizing database round-trips and respecting resource governance limits.

### 🔄 4. Resilient Fallback Logic
* **System Uptime > AI Features.**
* If the external AI service (Groq) experiences latency or downtime, the system automatically degrades gracefully to a deterministic **Rule-Based Engine**, ensuring no customer ticket is ever lost.

---

## 🛠️ Tech Stack

| Component | Technology |
| :--- | :--- |
| **Backend** | Java 17, Spring Boot 3.4, Spring Data JPA |
| **Frontend** | React.js, Axios, CSS3 |
| **Database** | H2 (In-Memory for CI/CD speed), Extensible to PostgreSQL |
| **AI / LLM** | Groq Cloud API (Llama 3.3 70b Versatile) |
| **DevOps** | Docker, Maven, Render Cloud |
| **Security** | Environment Variable Management (No hardcoded secrets) |

---

## 📸 Screenshots

### 1. High Priority Detection (Critical Incident)
*The AI detects a "Crash" context and immediately escalates priority to HIGH.*
![Screenshot Placeholder](https://via.placeholder.com/800x400?text=Upload+Your+Red+Ticket+Screenshot+Here)

### 2. Standard Workflow
*The AI handles routine billing questions with empathy and standard prioritization.*
![Screenshot Placeholder](https://via.placeholder.com/800x400?text=Upload+Your+Green+Ticket+Screenshot+Here)

---

## ⚙️ Local Installation & Setup

### Prerequisites
* Java JDK 17+
* Node.js & NPM
* Groq API Key (Free)

### 1. Clone the Repository
```bash
git clone [https://github.com/shyamal005/smart-ticket.git](https://github.com/shyamal005/smart-ticket.git)
cd smart-ticket
````

### 2\. Backend Setup

Navigate to the backend folder and run the Spring Boot application.
*Note: You must set your API key in `application.properties` or as an Environment Variable.*

```bash
cd backend
mvn spring-boot:run
```

*The server will start on port `8080`.*

### 3\. Frontend Setup

Open a new terminal window.

```bash
cd frontend
npm install
npm start
```

*The UI will launch on `http://localhost:3000`.*

-----

## 🔐 Security & Configuration

This project follows enterprise security standards by adhering to the **"12-Factor App"** methodology for config management.

  * **Local Development:** API Keys are injected via `application.properties` (GitIgnore enabled).
  * **Production (Render):** Keys are managed via securely encrypted **Environment Variables**.

-----

## 👤 Author

**Shyamal**

  * 💼 **LinkedIn:** https://www.linkedin.com/in/shyamal-kudupudi-001047228/
  * 📧 **Email:** kudipudishaymal0123@gmail.com

-----

*Built to demonstrate scalable Java architecture and AI integration for the Salesforce Ecosystem.*

```
```
