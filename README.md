# Java-Evaluation-Project-
Java CLI Banking System 
Overview
The Java CLI Banking System is a robust, console-based Java application designed to simulate basic banking operations. It provides a simple menu-driven interface allowing users to create accounts, authenticate securely using a PIN, transfer funds to other accounts, and view detailed account statements.
This project demonstrates core Object-Oriented Programming (OOP) principles and data management using Java Collections, all running purely in the terminal without the need for external databases or graphical interfaces.
Features
●	Account Management: Open a new bank account with an initial deposit and a secure 4-digit PIN. The system automatically generates a unique Account ID.
●	Secure Authentication: Users must log in using their valid Account ID and PIN before performing sensitive banking operations.
●	Fund Transfers: Transfer money to other existing accounts seamlessly. The system validates the recipient's account existence, ensures the transfer amount is valid (greater than zero), and checks for sufficient funds.
●	Account Statements: View a detailed history of all transactions (deposits, withdrawals, transfers) stamped with the date and time, along with the current account balance.
●	Interactive Menu: A looped, easy-to-navigate console menu that handles invalid inputs gracefully using custom exception handling.
Technologies and Concepts Used
●	Language: Java (JDK 8+)
●	Core Concepts:
○	Classes and Objects (Encapsulation, Inheritance)
○	Collections Framework (HashMap, ArrayList)
○	Custom Exception Handling
○	Date and Time API
○	Console Input Handling (Scanner)
Getting Started
Prerequisites
●	Java Development Kit (JDK) installed on your system.
●	A terminal or command prompt.
Installation and Execution
1.	Clone the repository or download the source code files to your local machine.
2.	Open your terminal or command prompt and navigate to the project directory.
3.	Compile the Java files:
javac Main.java

4.	Run the application:
java Main

Application Structure
Class / Component	Responsibility
Main	Displays the interactive menu, takes user input, and manages the execution flow.
Banking Service	Core logic processor: creates accounts, checks credentials, and processes transfers.
Account	Stores account details (ID, balance, PIN) and maintains the transaction history list.
Transaction	Keeps track of individual transaction metadata (type, amount, timestamp).
Banking Exception	Custom exception class for handling banking-specific error conditions (e.g., insufficient funds).
Sample Usage
Welcome to Java CLI Bank
MAIN MENU:
1. Open a New Account
2. Transfer Funds
3. View Account Statement
4. Exit Application
>> Select an option (1-4): 1
Enter full name: Rahul Sharma
Create a 4 digit pin: 1234
Enter initial deposit amount: $5000
SUCCESS! Your new Account ID is: 1001
Please keep this ID and your PIN safe.

Future Enhancements
●	Graphical User Interface (GUI): Transition from a CLI to a desktop interface using JavaFX or Swing.
●	Database Integration: Connect the system to a relational database like MySQL for persistent data storage.
●	Admin Controls: Add an administrator role to monitor and manage all bank accounts.
●	Expanded Transactions: Add standalone deposit and withdrawal options directly from the main menu.
●	Enhanced Security: Implement hashing algorithms to securely store user PINs instead of plaintext
