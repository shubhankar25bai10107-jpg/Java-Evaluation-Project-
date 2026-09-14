```java
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

class BankException extends Exception {
    public BankException(String message) {
        super(message);
    }
}

class TransactionRecord {
    private String type;
    private double amount;
    private LocalDateTime dateTime;

    public TransactionRecord(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter format =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return dateTime.format(format) + " | " + type + " | ₹"
                + String.format("%.2f", amount);
    }
}

class BankAccount {
    private int accountId;
    private String name;
    private String pin;
    private double balance;
    private List<TransactionRecord> history;

    public BankAccount(int accountId, String name, String pin, double balance) {
        this.accountId = accountId;
        this.name = name;
        this.pin = pin;
        this.balance = balance;
        this.history = new ArrayList<>();

        if (balance > 0) {
            history.add(
                    new TransactionRecord("Initial Deposit", balance)
            );
        }
    }

    public boolean checkPin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public void deposit(double amount) {
        balance += amount;
        history.add(new TransactionRecord("Deposit", amount));
    }

    public void withdraw(double amount) throws BankException {
        if (amount > balance) {
            throw new BankException("Not enough balance.");
        }

        balance -= amount;
        history.add(new TransactionRecord("Withdraw", amount));
    }

    public void showStatement() {
        System.out.println("\nAccount Statement");
        System.out.println("Name: " + name);
        System.out.println("--------------------------------------");

        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (TransactionRecord record : history) {
                System.out.println(record);
            }
        }

        System.out.println("--------------------------------------");
        System.out.println("Current Balance: ₹"
                + String.format("%.2f", balance));
    }
}

class BankSystem {
    private Map<Integer, BankAccount> accounts;
    private AtomicInteger nextAccountNumber;

    public BankSystem() {
        accounts = new HashMap<>();
        nextAccountNumber = new AtomicInteger(1000);
    }

    public int createAccount(String name, String pin, double amount) {
        int id = nextAccountNumber.incrementAndGet();

        BankAccount account =
                new BankAccount(id, name, pin, amount);

        accounts.put(id, account);

        return id;
    }

    public BankAccount login(int id, String pin)
            throws BankException {

        BankAccount account = accounts.get(id);

        if (account == null) {
            throw new BankException("Account not found.");
        }

        if (!account.checkPin(pin)) {
            throw new BankException("Wrong PIN.");
        }

        return account;
    }

    public void transfer(int fromId, String pin,
                         int toId, double amount)
            throws BankException {

        if (amount <= 0) {
            throw new BankException(
                    "Amount must be greater than zero."
            );
        }

        BankAccount sender = login(fromId, pin);
        BankAccount receiver = accounts.get(toId);

        if (receiver == null) {
            throw new BankException(
                    "Receiver account not found."
            );
        }

        sender.withdraw(amount);
        receiver.deposit(amount);
    }

    public void viewStatement(int id, String pin)
            throws BankException {

        BankAccount account = login(id, pin);
        account.showStatement();
    }
}

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankSystem bank = new BankSystem();

        System.out.println("Welcome to Java CLI Bank");

        boolean running = true;

        while (running) {

            System.out.println("\nMain Menu");
            System.out.println("1. Create Account");
            System.out.println("2. Transfer Money");
            System.out.println("3. View Statement");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();

            try {

                switch (choice) {

                    case "1":
                        System.out.print("Enter your name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter a 4-digit PIN: ");
                        String pin = sc.nextLine();

                        System.out.print("Enter initial deposit: ₹");
                        double deposit =
                                Double.parseDouble(sc.nextLine());

                        int id =
                                bank.createAccount(
                                        name,
                                        pin,
                                        deposit
                                );

                        System.out.println(
                                "Account created successfully!"
                        );
                        System.out.println(
                                "Your Account ID is: " + id
                        );
                        break;

                    case "2":
                        System.out.print("Enter your Account ID: ");
                        int senderId =
                                Integer.parseInt(sc.nextLine());

                        System.out.print("Enter your PIN: ");
                        String senderPin = sc.nextLine();

                        System.out.print("Enter receiver Account ID: ");
                        int receiverId =
                                Integer.parseInt(sc.nextLine());

                        System.out.print("Enter amount: ₹");
                        double amount =
                                Double.parseDouble(sc.nextLine());

                        bank.transfer(
                                senderId,
                                senderPin,
                                receiverId,
                                amount
                        );

                        System.out.println(
                                "Money transferred successfully!"
                        );
                        break;

                    case "3":
                        System.out.print("Enter Account ID: ");
                        int statementId =
                                Integer.parseInt(sc.nextLine());

                        System.out.print("Enter PIN: ");
                        String statementPin = sc.nextLine();

                        bank.viewStatement(
                                statementId,
                                statementPin
                        );
                        break;

                    case "4":
                        System.out.println(
                                "Thank you for using Java CLI Bank!"
                        );
                        running = false;
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. Try again."
                        );
                }

            } catch (BankException e) {

                System.out.println(
                        "Error: " + e.getMessage()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }

        sc.close();
    }
}
```
