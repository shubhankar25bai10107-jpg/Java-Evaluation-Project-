# Java CLI Banking System

A simple **console-based banking application built in Java** that simulates basic banking operations such as account creation, secure PIN authentication, fund transfers, and viewing account statements.

The project is designed to demonstrate **Object-Oriented Programming (OOP), Java Collections, exception handling, and the Java Date and Time API** without using an external database or graphical interface.

---

## Features

### 1. Account Management

* Create a new bank account.
* Enter the customer's full name.
* Set a secure 4-digit PIN.
* Make an initial deposit.
* Automatically generate a unique Account ID.

### 2. Secure Authentication

* Users must provide their Account ID and PIN.
* Authentication is required before performing sensitive banking operations.
* Invalid credentials are handled appropriately.

### 3. Fund Transfers

* Transfer money to another existing account.
* Verify that the recipient account exists.
* Validate that the transfer amount is greater than zero.
* Check whether the sender has sufficient funds.

### 4. Account Statements

* View the current account balance.
* View a history of transactions.
* Transactions include their type, amount, and date/time.

### 5. Interactive CLI Menu

* Simple menu-driven interface.
* Runs directly in the terminal.
* Handles invalid inputs using exception handling.

---

## Technologies Used

| Technology / Concept | Usage                                   |
| -------------------- | --------------------------------------- |
| Java                 | Main programming language               |
| JDK 8+               | Development environment                 |
| HashMap              | Storing and accessing bank accounts     |
| ArrayList            | Maintaining transaction history         |
| Scanner              | Reading console input                   |
| LocalDateTime        | Recording transaction date and time     |
| DateTimeFormatter    | Formatting timestamps                   |
| Custom Exceptions    | Handling banking-related errors         |
| OOP                  | Organizing the application into classes |

---

## OOP Concepts Demonstrated

The project makes use of several important Object-Oriented Programming concepts:

### Encapsulation

Account and transaction information is maintained inside classes using appropriate access modifiers and methods.

### Classes and Objects

The system is divided into classes such as `Account`, `Transaction`, `BankingService`, and `Main`.

### Inheritance

Custom exception handling is implemented using a banking-specific exception class that extends Java's `Exception` class.

### Abstraction

Banking operations such as account creation and fund transfers are handled through the banking service rather than directly from the main program.

---

## Application Structure

| Class / Component  | Responsibility                                                   |
| ------------------ | ---------------------------------------------------------------- |
| `Main`             | Displays the menu, accepts user input, and controls program flow |
| `BankingService`   | Handles account creation, authentication, and fund transfers     |
| `Account`          | Stores account information and transaction history               |
| `Transaction`      | Stores transaction type, amount, and timestamp                   |
| `BankingException` | Handles banking-specific errors                                  |

---

## Project Flow

```text
Start
  |
  v
Display Main Menu
  |
  +----> Open New Account
  |          |
  |          v
  |     Enter Name & PIN
  |          |
  |          v
  |     Initial Deposit
  |          |
  |          v
  |     Generate Account ID
  |
  +----> Transfer Funds
  |          |
  |          v
  |     Enter Account ID & PIN
  |          |
  |          v
  |     Authenticate User
  |          |
  |          v
  |     Enter Recipient & Amount
  |          |
  |          v
  |     Validate & Transfer
  |
  +----> View Statement
  |          |
  |          v
  |     Authenticate User
  |          |
  |          v
  |     Display Transactions
  |
  +----> Exit
             |
             v
            End
```

---

## Getting Started

### Prerequisites

Before running the project, make sure you have:

* **Java Development Kit (JDK) 8 or later**
* A terminal or command prompt
* A text editor or Java IDE such as IntelliJ IDEA, Eclipse, or VS Code

Check your Java installation using:

```bash
java -version
```

---

## Installation and Execution

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/Java-Evaluation-Project-.git
```

Move into the project directory:

```bash
cd Java-Evaluation-Project-
```

### 2. Compile the Program

If all classes are contained in the project directory, compile the main program using:

```bash
javac Main.java
```

### 3. Run the Application

```bash
java Main
```

---

## Sample Usage

```text
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
```

---

## Account Transfer Example

A user can authenticate using their Account ID and PIN and then transfer money to another registered account.

The system checks:

1. Whether the sender account exists.
2. Whether the PIN is correct.
3. Whether the recipient account exists.
4. Whether the transfer amount is greater than zero.
5. Whether the sender has sufficient funds.

If all conditions are satisfied, the transfer is completed and recorded in the transaction history.

---

## Transaction History

Each transaction stores information such as:

```text
Transaction Type
Amount
Date and Time
```

Example:

```text
ACCOUNT STATEMENT
------------------------------
Transfer        $1000.00
2026-09-14 14:35:20

Current Balance: $4000.00
```

---

## Exception Handling

The project uses a custom `BankingException` class to handle banking-specific errors.

Examples include:

* Invalid Account ID
* Incorrect PIN
* Invalid transfer amount
* Insufficient balance
* Recipient account not found
* Invalid menu selection

This makes the application more reliable and prevents unexpected program termination during normal user errors.

---

## Data Storage

The current version stores account and transaction information **in memory using Java Collections**.

For example:

* `HashMap` → Stores account information.
* `ArrayList` → Stores transaction history.

Because there is no external database, all data is lost when the application is closed.

---

## Limitations

* Data is not permanently stored.
* The application only runs through the command line.
* PINs are currently stored as plain values.
* There are no dedicated deposit and withdrawal options from the main menu.
* No administrator functionality is currently available.
* The application does not use a database.

---

## Future Enhancements

The project can be further improved by adding:

### GUI

Develop a graphical interface using **JavaFX or Swing**.

### Database Integration

Use **MySQL** or another relational database to permanently store accounts and transactions.

### Admin Controls

Add an administrator role for monitoring and managing accounts.

### Deposit and Withdrawal

Add dedicated options for depositing and withdrawing money.

### Enhanced Security

Use secure hashing techniques instead of storing PINs directly.

### Persistent Transaction Records

Store transaction information in a database so that account statements remain available after restarting the application.

---

## Learning Outcomes

Through this project, the following concepts can be practiced:

* Java classes and objects
* Encapsulation
* Inheritance
* Collections Framework
* `HashMap` and `ArrayList`
* Exception handling
* Custom exceptions
* `Scanner` for user input
* Date and Time API
* Menu-driven programming
* Basic banking logic
* Modular program design

---

## Conclusion

The **Java CLI Banking System** provides a practical implementation of basic banking operations while demonstrating important Java programming concepts. It combines Object-Oriented Programming, collections, exception handling, authentication, and transaction management into a single console-based application.

The project can serve as a foundation for developing a more advanced banking application with database support, graphical interfaces, stronger security, and additional banking features.

