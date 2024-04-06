package Classes;

/**
 * @author <Nguyen Thanh Tung - s3979489>
 * Reference: https://github.com/VINAYKUMARKUNDER/Insurance-Management-System.git and 
 * https://youtu.be/xNeOHmqNVus?si=4L5anBRVpkQJviVH
 */

import Interface.generateID;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BankingInfo implements generateID {
    private String ID;
    private String BankName;
    private long AccountNumber;
    private static Random random = new Random();
    private static List<BankingInfo> banks = new ArrayList<>();

    public BankingInfo() {
    }
    public BankingInfo(String ID, String bankName, long accountNumber) {
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

    public void setAccountNumber(long accountNumber) {
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
        long accountNumber = scanner.nextLong();
        scanner.nextLine();

        // Create a new BankingInfo object
        BankingInfo bankid = new BankingInfo();
        String id = bankid.IDGenerator();
        BankingInfo newBank = new BankingInfo(id, bankName, accountNumber);

        // Save the new BankingInfo object to the text file
        LoadSaveData loadSaveData = new LoadSaveData();
        loadSaveData.saveBankingInfo(newBank);

        System.out.println("Banking information created successfully.");
    }
    public static void updateBank(Scanner scanner) {
        System.out.println("Enter the ID of the bank you want to update (b-xxxxxxx):");
        String id = scanner.nextLine();

        // Create an instance of LoadSaveData to call the loadBankingInfo method
        LoadSaveData loadSaveData = new LoadSaveData();

        // Load the bank information from the text file
        List<BankingInfo> banks = loadSaveData.loadBankingInfo();

        // Find the bank in the list of banks
        for (BankingInfo bank : banks) {
            if (bank.getID().equals(id)) {
                // If the bank is found, ask for new details and update the bank
                System.out.println("Enter new Bank Name (or type 'skip' to skip):");
                String bankName = scanner.nextLine();
                if (!bankName.isEmpty() && !bankName.equalsIgnoreCase("skip")) {
                    bank.setBankName(bankName);
                }

                System.out.println("Enter new Account Number (or type 'skip' to skip):");
                String accountNumberStr = scanner.nextLine();
                if (!accountNumberStr.isEmpty() && !accountNumberStr.equalsIgnoreCase("skip")) {
                    long accountNumber = Long.parseLong(accountNumberStr);
                    bank.setAccountNumber(accountNumber);
                }

                // Update the bank information in the text file
                loadSaveData.updateBankingInfo(banks);

                System.out.println("Bank updated successfully");
                return;
            }
        }

        // If the bank is not found, inform the user
        System.out.println("Bank with ID: " + id + " not found.");
    }
    public static void readBank(Scanner scanner) {
        System.out.println("Enter the ID of the bank you want to read (b-xxxxxxx):");
        String id = scanner.nextLine();

        // Load the bank information from the text file
        LoadSaveData loadSaveData = new LoadSaveData();
        List<BankingInfo> banks = loadSaveData.loadBankingInfo();

        // Search for the bank in the list of banks
        for (BankingInfo bank : banks) {
            if (bank.getID().equals(id)) {
                // If the bank is found, print the bank's details
                System.out.println(bank);
                return;
            }
        }

        // If the bank is not found, print an error message
        System.out.println("Bank with ID: " + id + " not found.");
    }
    public static void readAllBanks() {
        // Load the bank information from a file
        LoadSaveData loadSaveData = new LoadSaveData();
        List<BankingInfo> banks = loadSaveData.loadBankingInfo();

        // If there are no banks, inform the user
        if (banks.isEmpty()) {
            System.out.println("No banks found.");
            return;
        }

        // Print each bank's details
        for (BankingInfo bank : banks) {
            System.out.println(bank.toString());
        }
    }


    @Override
    public String toString() {
        return  '{' +
                "ID=" + ID +
                ", BankName=" + BankName +
                ", AccountNumber=" + AccountNumber +
                '}';
    }
}
