# Pharmacy Management System

## Overview

The Pharmacy Management System is a Java-based desktop application designed to streamline the management of drug inventory, sales, and purchases in a pharmacy. This system provides functionalities for managing drugs, suppliers, and customers, as well as generating various reports to monitor the performance of the pharmacy.

## Features

- **Drug Management**: Add, update, delete, and search for drugs.
- **Supplier Management**: Load and display supplier information.
- **Customer Management**: Add new customers.
- **Sales Management**: Record sales transactions, including drug, customer, quantity, and total amount.
- **Purchase Management**: Record purchase transactions, including drug, supplier, quantity, and total amount.
- **Stock Alerts**: Alert when drug stock falls below the minimum stock level.
- **Reporting**: Generate sales and purchase reports within a specified date range.

## Technologies Used

- Java
- MySQL
- Swing (Java's GUI toolkit)
- JDBC (Java Database Connectivity)

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- MySQL Database
- An IDE (such as IntelliJ IDEA, Eclipse, or NetBeans)

### Setting Up the Database

1. Install MySQL and create a database named `pharmacy_management`.
2. Run the provided SQL script to create the necessary tables and insert initial data:
   ```sql
   CREATE DATABASE pharmacy_management;

   USE pharmacy_management;

   CREATE TABLE suppliers (
       supplier_id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(255) NOT NULL
   );

   CREATE TABLE drugs (
       drug_id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       description TEXT,
       price DECIMAL(10, 2) NOT NULL,
       supplier_id INT,
       current_stock INT,
       min_stock_level INT,
       FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id)
   );

   CREATE TABLE customers (
       customer_id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(255) NOT NULL
   );

   CREATE TABLE purchase_history (
       purchase_id INT AUTO_INCREMENT PRIMARY KEY,
       drug_id INT,
       purchase_date DATE,
       quantity INT,
       total_amount DECIMAL(10, 2),
       FOREIGN KEY (drug_id) REFERENCES drugs(drug_id)
   );

   CREATE TABLE sales (
       sale_id INT AUTO_INCREMENT PRIMARY KEY,
       drug_id INT,
       sale_date DATE,
       quantity INT,
       total_amount DECIMAL(10, 2),
       customer_id INT,
       FOREIGN KEY (drug_id) REFERENCES drugs(drug_id),
       FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
   );
   ```

### Configuring the Application

1. Clone the repository:
   ```sh
   git clone https://github.com/omega-cpu/JAVAProject1.git
   ```

2. Open the project in your preferred IDE.

3. Update the database connection settings in the `Connect` method in each Java class (`Drugs.java`, `purchase.java`, `sales.java`, etc.):
   ```java
   con = DriverManager.getConnection(
       "jdbc:mysql://your-database-url:3306/pharmacy_management?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", 
       "your-username", 
       "your-password"
   );
   ```

### Running the Application

1. Compile and run the main class:
   ```sh
   javac com/mycompany/porjectwork1/PharmacyManagementSystem.java
   ```

## Usage

- **Drug Management**: Use the Drugs module to manage drug inventory. You can add, update, delete, and search for drugs.
- **Sales Management**: Use the Sales module to record sales transactions. Ensure that drug stock is updated accordingly.
- **Purchase Management**: Use the Purchase module to record purchase transactions and update drug stock.
- **Reports**: Generate sales and purchase reports based on a specified date range. Access the Reports module to view these reports.

## Performance Analysis

### Connect() Method
- **Time Complexity**: O(1)
- **Space Complexity**: O(1)

### update_table() Method
- **Time Complexity**: O(n * c)
- **Space Complexity**: O(n * c)

### setupSearchFunctionality() Method
- **Time Complexity**: O(n)
- **Space Complexity**: O(n)

### checkStockLevels() Method
- **Time Complexity**: O(m)
- **Space Complexity**: O(m)

### Overall Performance
- **Best Case**: Ω(1)
- **Average Case**: O(n * c) for `update_table()`, O(n) for `setupSearchFunctionality()`, O(m) for `checkStockLevels()`
- **Worst Case**: O(n * c), O(n), O(m)

## Contribution

If you want to contribute to this project, please fork the repository and submit a pull request. For major changes, please open an issue first to discuss what you would like to change.


## Acknowledgements

- This project uses the MySQL JDBC Driver.
- The GUI is built using Java Swing.
- Date picker functionality is provided by the JDateChooser component from the JCalendar library.
