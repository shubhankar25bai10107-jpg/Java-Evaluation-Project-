import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

class BankingException extends Exception {
    public BankingException(String message) {
        super(message);
    }
}

class Transaction {
    private String transactionType;
    private double amount;
    private LocalDateTime timestamp;

    public Transaction(String type, double amount) {
        this.transactionType = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = timestamp.format(formatter);
        return formattedDate + " | " + transactionType + " | $" + String.format("%.2f", amount);
    }
}

class Account {
    private int accountId;
    private String accountHolder;
    private String securityPin;
    private double currentBalance;
    private List<Transaction> transactionHistory;

    public Account(int id, String name, String pin, double initialBalance) {
        this.accountId = id;
        this.accountHolder = name;
        this.securityPin = pin;
        this.currentBalance = initialBalance;
        this.transactionHistory = new ArrayList<>();

        if (initialBalance > 0) {
            transactionHistory.add(new Transaction("INITIAL DEPOSIT", initialBalance));
        }
    }

    public int getAccountId() {
        return accountId;
    }

    public boolean isPinValid(String inputPin) {
        return this.securityPin.equals(inputPin);
    }

    public void addFunds(double amount) {
        this.currentBalance + = amount;
        transactionHistory.add(new Transaction("DEPOSIT", amount));
    }

    public void removeFunds(double amount) throws BankingException {
        if (amount > this.currentBalance) {
            throw new BankingException("Not enough funds in the account.");
        }
        this.currentBalance - = amount;
        transactionHistory.add(new Transaction("WITHDRAW", amount));
    }

    public void showStatement() {
        System.out.println("\n====================================== ");
        System.out.println("   Account Statement: " + accountHolder);
        System.out.println("====================================== ");

        if (transactionHistory.isEmpty()) {
            System.out.println("No recent transactions.");
        } else {
            for (Transaction t : transactionHistory) {
                System.out.println(t.toString());
            }
        }

        System.out.println("--------------------------------------");
        System.out.println("Current Balance: $" + String.format("%.2f", currentBalance));
        System.out.println("====================================== \n");
    }
}

class BankingService {
    private Map<Integer, Account> accountDatabase = new HashMap<>();
    private AtomicInteger nextAccountNumber = new AtomicInteger(1000);

    public int createNewAccount(String name, String pin, double startingAmount) {
        int newId = nextAccountNumber.incrementAndGet();
        Account newAccount = new Account(newId, name, pin, startingAmount);
        accountDatabase.put(newId, newAccount);
        return newId;
    }

    public Account login(int id, String pin) throws BankingException {
        Account acc = accountDatabase.get(id);

        if (acc == null) {
            throw new BankingException("Account not found.");
        }
        if (!acc.isPinValid(pin)) {
            throw new BankingException("Incorrect PIN.");
        }
        return acc;
    }

    public void processTransfer(int senderId, String senderPin, int receiverId, double transferAmount) throws BankingException {
        if (transferAmount <= 0) {
            throw new BankingException("Transfer amount must be greater than zero.");
        }

        Account sender = login(senderId, senderPin);
        Account receiver = accountDatabase.get(receiverId);

        if (receiver == null) {
            throw new BankingException("Recipient account does not exist.");
        }

        sender.removeFunds(transferAmount);
        receiver.addFunds(transferAmount);
    }

    public void generateStatement(int id, String pin) throws BankingException {
        Account acc = login(id, pin);
        acc.showStatement();
    }
}

public class Main {
    public static void main(String[] args) {
        BankingService bank = new BankingService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("**************************************");
        System.out.println("*      Welcome to Java CLI Bank      *");
        System.out.println("**************************************");

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nMAIN MENU:");
            System.out.println("1. Open a New Account");
            System.out.println("2. Transfer Funds");
            System.out.println("3. View Account Statement");
            System.out.println("4. Exit Application");
            System.out.print(">> Select an option (1-4): ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        System.out.print("Enter full name: ");
                        String name = scanner.nextLine();

                        System.out.print("Create a 4 digit PIN: ");
                        String pin = scanner.nextLine();

                        System.out.print("Enter initial deposit : $");
                        double deposit = Double.parseDouble(scanner.nextLine());

                        int accId = bank.createNewAccount(name, pin, deposit);
                        System.out.println(" Your new Account ID is: " + accId);
                        break;

                    case "2":
                        System.out.print("Enter your Account ID: ");
                        int fromId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter your PIN: ");
                        String myPin = scanner.nextLine();

                        System.out.print("Enter recipient's Account ID: ");
                        int toId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter amount to transfer: $");
                        double amount = Double.parseDouble(scanner.nextLine());

                        bank.processTransfer(fromId, myPin, toId, amount);
                        System.out.println("\nSUCCESS: Transfer completed.");
                        break;

                    case "3":
                        System.out.print("Enter your Account ID: ");
                        int queryId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter your PIN: ");
                        String queryPin = scanner.nextLine();

                        bank.generateStatement(queryId, queryPin);
                        break;

                    case "4":
                        System.out.println("\nThank you for using Java CLI Bank. Goodbye!");
                        isRunning = false;
                        break;

                    default:
                        System.out.println("\nINVALID OPTION: Please type 1, 2, 3, or 4 and press Enter.");
                }
            } catch (BankingException e) {
                System.out.println("\nTRANSACTION FAILED: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("\nINPUT ERROR: Please enter valid numbers only.");
            }
        }
        scanner.close();
    }
}
