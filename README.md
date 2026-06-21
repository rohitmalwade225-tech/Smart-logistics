# 🚚 Smart Logistics & Supply Chain Management System

A modern enterprise-grade Logistics & Supply Chain Management System built using Spring Boot and REST APIs for managing inventory, warehouses, shipments, vehicles, suppliers, customers, and order fulfillment operations.

---

## 📌 Project Overview

The Smart Logistics Management System helps organizations manage end-to-end logistics operations efficiently.

The application provides centralized control over:

- Inventory Management
- Warehouse Management
- Shipment Tracking
- Vehicle Management
- Customer Management
- Supplier Management
- Order Processing
- Dashboard Analytics
- Authentication & Authorization

---

## ✨ Key Features

### 🔐 Authentication & Security
- JWT Authentication
- Secure Login
- Role-Based Access Control
- Spring Security Integration

### 📦 Inventory Management
- Product Management
- Stock Tracking
- Inventory Monitoring
- Stock Movement History

### 🏭 Warehouse Management
- Multiple Warehouse Support
- Warehouse Capacity Tracking
- Inventory Allocation

### 🚛 Shipment Management
- Shipment Creation
- Shipment Tracking
- Delivery Status Monitoring

### 🚚 Vehicle Management
- Fleet Management
- Vehicle Tracking
- Transport Operations

### 👥 Customer Management
- Customer Registration
- Order History
- Customer Information Management

### 🏢 Supplier Management
- Supplier Records
- Supplier Product Management

### 📊 Dashboard & Analytics
- Logistics Statistics
- Inventory Overview
- Shipment Reports
- Operational Insights

---

## 🛠️ Technology Stack

| Technology | Version |
|------------|----------|
| Java | 17+ |
| Spring Boot | 3.x |
| Spring Security | Latest |
| Spring Data JPA | Latest |
| Hibernate | Latest |
| MySQL | 8+ |
| Maven | Latest |
| Bootstrap | 5 |
| REST API | Yes |
| JWT | Yes |

---

## 📂 Project Structure

```text
src/main/java/com/logistics
│
├── config
├── controller/rest
├── dto
├── entity
├── repository
├── service
├── security
├── util
└── exception
```

---

## 📸 Application Screenshots

### Dashboard

![Dashboard](screenshots/dashboard.png)

### Inventory Management

![Inventory](screenshots/inventory.png)

### Warehouse Management

![Warehouse](screenshots/warehouse.png)

### Shipment Tracking

![Shipment](screenshots/shipment.png)

### Vehicle Management

![Vehicle](screenshots/vehicle.png)

### Customer Management

![Customer](screenshots/customer.png)

---

## 🚀 Getting Started

### Clone Repository

```bash
git clone https://github.com/YOUR_USERNAME/smart-logistics.git
```

### Navigate to Project

```bash
cd smart-logistics
```

### Configure Database

Update:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/logisticsdb
spring.datasource.username=root
spring.datasource.password=password
```

### Run Application

```bash
mvn spring-boot:run
```

---

## 🔑 Default Credentials

### Admin

```text
Username : admin
Password : admin123
```

> Change credentials after deployment.

---

## 📡 REST APIs

The project provides REST APIs for:

- Authentication
- Products
- Inventory
- Warehouses
- Customers
- Suppliers
- Orders
- Shipments
- Vehicles
- Dashboard

---

## 🎯 Business Benefits

- Real-Time Inventory Visibility
- Improved Supply Chain Efficiency
- Shipment Tracking
- Warehouse Optimization
- Reduced Operational Costs
- Better Customer Service

---

## 👨‍💻 Author

**Rohit Malwade**

Final Year Project

Smart Logistics & Supply Chain Management System

---

## ⭐ Future Enhancements

- Live GPS Tracking
- AI-Based Demand Forecasting
- Email Notifications
- SMS Alerts
- Mobile Application
- Advanced Analytics Dashboard

---

## 📜 License

This project is developed for educational and learning purposes.
