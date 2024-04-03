package Classes;

/**
 * @author <Nguyen Thanh Tung - s3979489>
 */

import Interface.generateID;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BankingInfo implements generateID {
    private String ID;
    private String BankName;
    private double AccountNumber;
    private static Random random = new Random();
    private static List<BankingInfo> banks = new ArrayList<>();

    public BankingInfo() {
    }
    public BankingInfo(String ID, String bankName, double accountNumber) {
        this.ID = ID;
        this.BankName = bankName;
        this.AccountNumber = accountNumber;
    }
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public double getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(double accountNumber) {
        AccountNumber = accountNumber;
    }
    @Override
    public String IDGenerator() {
        int number = random.nextInt(9_000_000) + 1_000_000;
        return "b-" + number;
    }

    public static void createBank(Scanner scanner) {

        System.out.println("Enter the Bank Name:");
        String bankName = scanner.nextLine();

        System.out.println("Enter the Account Number:");
        double accountNumber = scanner.nextDouble();
        scanner.nextLine(); // consume newline left-over

        // Create a new BankingInfo object
        BankingInfo bankid = new BankingInfo();
        String id = bankid.IDGenerator();
        BankingInfo newBank = new BankingInfo(id, bankName, accountNumber);

        // Save the new BankingInfo object to a file
        LoadSaveData loadSaveData = new LoadSaveData();
        loadSaveData.saveBankingInfo(newBank, "bankingInfo.txt");

        System.out.println("Banking information created successfully.");
    }
    public static void updateBank(Scanner scanner) {
        System.out.println("Enter the ID of the bank you want to update:");
        String id = scanner.nextLine();

        // Find the bank in the list of banks
        for (BankingInfo bank : banks) {
            if (bank.getID().equals(id)) {
                // If the bank is found, ask for new details and update the bank
                System.out.println("Enter new Bank Name:");
                String bankName = scanner.nextLine();

                System.out.println("Enter new Account Number:");
                double accountNumber = scanner.nextDouble();
                scanner.nextLine(); // consume newline left-over

                bank.setBankName(bankName);
                bank.setAccountNumber(accountNumber);

                // Save the updated bank info
                LoadSaveData loadSaveData = new LoadSaveData();
                loadSaveData.saveBankingInfo(bank, "bankingInfo.txt");

                System.out.println("Bank updated successfully with ID: " + id);
                return;
            }
        }

        // If the bank is not found, inform the user
        System.out.println("Bank with ID: " + id + " not found.");
    }
    public static void readBank(Scanner scanner) {
        System.out.println("Enter the ID of the bank you want to read:");
        String id = scanner.nextLine();

        // Load the bank information from a file
        LoadSaveData loadSaveData = new LoadSaveData();
        List<BankingInfo> banks = loadSaveData.loadBankingInfo("bankingInfo.txt");

        // Search for the bank in the list of banks
        for (BankingInfo bank : banks) {
            if (bank.getID().equals(id)) {
                // If the bank is found, print the bank's details
                System.out.println(bank.toString());
                return;
            }
        }

        // If the bank is not found, print an error message
        System.out.println("Bank with ID: " + id + " not found.");
    }

    @Override
    public String toString() {
        return  '{' +
                "ID=" + ID + '\'' +
                ", BankName=" + BankName + '\'' +
                ", AccountNumber=" + AccountNumber +
                '}';
    }
}
