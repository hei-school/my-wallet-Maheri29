import java.util.ArrayList;
import java.util.Scanner;

public class Wallet {
    private double balance;
    private ArrayList<Transaction> transactions;
    private CINInfo cinInfo;
    private String currentCategory;

    public Wallet() {
        this.balance = 0;
        this.transactions = new ArrayList<>();
        this.cinInfo = new CINInfo("", "", "");
        this.currentCategory = "Money";
    }

    public void deposit(double amount) {
        this.balance += amount;
        this.transactions.add(new Transaction("Recharge", amount));
        System.out.println("Dépôt réussi");
    }

    public void withdraw(double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            this.transactions.add(new Transaction("Retrait", amount));
            System.out.println("Retrait réussi");
        } else {
            System.out.println("Fonds insuffisants");
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public double getTotalExpenses() {
        double totalExpenses = 0;
        for (Transaction transaction : this.transactions) {
            if (transaction.getType().equals("Retrait")) {
                totalExpenses += transaction.getAmount();
            }
        }
        return totalExpenses;
    }

    public void getTransactionHistory() {
        System.out.println("Suivi des transactions :");
        for (Transaction transaction : this.transactions) {
            System.out.println(transaction.getType() + ": " + transaction.getAmount());
        }
    }

    public void setCINInfo(String nom, String prenom, String numeroCIN) {
        this.cinInfo = new CINInfo(nom, prenom, numeroCIN);
        System.out.println("Informations CIN enregistrées avec succès");
    }

    public void displayCINInfo() {
        System.out.println("Informations CIN :");
        System.out.println("Nom: " + this.cinInfo.getNom());
        System.out.println("Prénom: " + this.cinInfo.getPrenom());
        System.out.println("Numéro CIN: " + this.cinInfo.getNumeroCIN());
    }

    public static void main(String[] args) {
        Wallet myWallet1 = new Wallet();
        Wallet myWallet2 = new Wallet();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu :");
            System.out.println("1. Money");
            System.out.println("2. CIN");
            System.out.println("0. Quitter");

            System.out.print("Choisissez une catégorie : ");
            String categoryChoice = scanner.nextLine();

            switch (categoryChoice) {
                case "1":
                    myWallet1.currentCategory = "Money";
                    handleMoneyCategory(myWallet1);
                    break;
                case "2":
                    myWallet1.currentCategory = "CIN";
                    handleCINCategory(myWallet1);
                    break;
                case "0":
                    System.out.println("Au revoir !");
                    System.exit(0);
                default:
                    System.out.println("Option invalide. Veuillez choisir une option valide.");
            }
        }
    }

    private static void handleMoneyCategory(Wallet wallet) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Money Menu :");
            System.out.println("1. Recharge");
            System.out.println("2. Retrait");
            System.out.println("3. Consultation de solde");
            System.out.println("4. Total des dépenses");
            System.out.println("5. Suivi des transactions");
            System.out.println("0. Retour au menu principal");

            System.out.print("Choisissez une option : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Entrez le montant à déposer : ");
                    double depositAmount = scanner.nextDouble();
                    wallet.deposit(depositAmount);
                    break;
                case "2":
                    System.out.print("Entrez le montant à retirer : ");
                    double withdrawAmount = scanner.nextDouble();
                    wallet.withdraw(withdrawAmount);
                    break;
                case "3":
                    System.out.println("Solde actuel : " + wallet.getBalance());
                    break;
                case "4":
                    System.out.println("Total des dépenses : " + wallet.getTotalExpenses());
                    break;
                case "5":
                    wallet.getTransactionHistory();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Option invalide. Veuillez choisir une option valide.");
            }
            scanner.nextLine(); // Consume the newline character left in the buffer
        }
    }

    private static void handleCINCategory(Wallet wallet) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("CIN Menu :");
            System.out.println("1. Enregistrer les informations CIN");
            System.out.println("2. Afficher les informations CIN");
            System.out.println("0. Retour au menu principal");

            System.out.print("Choisissez une option : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Entrez votre nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Entrez votre prénom : ");
                    String prenom = scanner.nextLine();
                    System.out.print("Entrez votre numéro CIN : ");
                    String numeroCIN = scanner.nextLine();
                    wallet.setCINInfo(nom, prenom, numeroCIN);
                    break;
                case "2":
                    wallet.displayCINInfo();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Option invalide. Veuillez choisir une option valide.");
            }
        }
    }
}

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}

class CINInfo {
    private String nom;
    private String prenom;
    private String numeroCIN;

    public CINInfo(String nom, String prenom, String numeroCIN) {
        this.nom = nom;
        this.prenom = prenom;
        this.numeroCIN = numeroCIN;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNumeroCIN() {
        return numeroCIN;
    }
}
