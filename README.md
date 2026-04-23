# 🚀 Selenium Automation Framework

## 📌 Overview
This project is a **Selenium Automation Framework** built using:

- Java
- Selenium WebDriver
- TestNG
- Maven

The framework is designed using **Page Object Model (POM)** to ensure:
- Maintainability  
- Reusability  
- Scalability  

---

## 🏗️ Project Structure

Selenium_Framework
│── src
│   ├── main
│   │   └── java
│   └── test
│       └── java
│           ├── tests
│           ├── pages
│           ├── utils
│           └── base
│
│── testng.xml
│── pom.xml
│── README.md

---

## ⚙️ Prerequisites

- Java JDK 8+
- Maven
- IntelliJ IDEA
- Chrome / Edge browser

---

## 🔧 Setup Instructions

### 1. Clone the repository

git clone https://github.com/mahmoudsaad31/Selenium_Framework.git
cd Selenium_Framework

---

### 2. Install dependencies

mvn clean install

---

### 3. Run Tests

mvn test

---

## 🧪 Framework Features

- Page Object Model (POM)
- TestNG integration
- Reusable methods
- Wait handling
- Screenshot on failure
- Logging (Log4j)
- Allure Reports
- Retry Analyzer

---

## 📊 Allure Report

allure serve allure-results

---

# ⚙️ Jenkins Integration (CI/CD)

## 🛠️ Steps

1. Install Jenkins and open http://localhost:8080  
2. Install Plugins:
   - Maven Integration
   - Git Plugin
   - Allure Plugin  

3. Configure Maven from Global Tool Configuration  

4. Create New Job (Freestyle Project)  

5. Connect GitHub repo:
https://github.com/mahmoudsaad31/Selenium_Framework.git  

6. Add Build Step:
clean test  

7. Add Allure Report:
allure-results  

8. Click Build Now  

---

## 👨‍💻 Author

Mahmoud Saad
