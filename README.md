# Banking System Application

This is a simple banking system application implemented in Java with JDBC for database interaction.

## Prerequisites

Before running the application, ensure you have the following installed on your machine:

- Java Development Kit (JDK) version 8 or higher
- MySQL Database Server
- MySQL JDBC Driver

## Setup

1. **Clone the Repository**:
(https://github.com/iamthejahid/Banking-system-java-assignment)


2. **Download MySQL JDBC Driver**:
- Download the MySQL JDBC driver JAR file from [here](https://dev.mysql.com/downloads/connector/j/).
- Place the downloaded JAR file in the `lib` folder of the project.

3. **Create MySQL Database**:
- Log in to your MySQL database server.
- Create a new database named `BankingSystem` using the following SQL command:
  ```
  CREATE DATABASE BankingSystem;
  ```

4. **Run SQL Scripts**:
- Execute the SQL scripts located in the `sql` folder to create the necessary database tables and insert initial data.

5. **Compile Java Files**:
- Compile the Java source files using the following command:
  ```
  javac -cp lib/mysql-connector-java.jar:. src/*.java src/com/banking/*.java src/com/banking/service/*.java src/com/banking/util/*.java
  ```

6. **Run the Application**:
- Run the application using the following command:
  ```
  java -cp lib/mysql-connector-java.jar:. Main
  ```

## Usage

Once the application is running, follow the on-screen instructions to interact with the banking system. You can perform transactions, view account details, and more.

## Contributing

Contributions are welcome! Feel free to submit pull requests or open issues for any improvements or fixes.
