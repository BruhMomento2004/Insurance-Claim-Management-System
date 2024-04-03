package Classes;

/**
 * @author <Nguyen Thanh Tung - s3979489> 
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import Enum.Status;
import Interface.ClaimProcessManager;
import Interface.generateID;

public class Claim implements ClaimProcessManager, generateID {
    private String ID;
    private Date ClaimDate;
    private String InsuredPerson;
    private int CardNumber;
    private Date ExamDate;
    private List<String> Documents;
    private double ClaimAmount;
    private Status status;
    private String BankingInfo;
    private static List<Claim> claims = new ArrayList<>();
    private static Random random = new Random();


    public Claim() {
    }
    public Claim(String ID, Date claimDate, String insuredPerson, int cardNumber, Date examDate, List<String> documents, double claimAmount, Status status, String bankingInfo) {
        this.ID = ID;
        this.ClaimDate = claimDate;
        this.InsuredPerson = insuredPerson;
        this.CardNumber = cardNumber;
        this.ExamDate = examDate;
        this.Documents = documents;
        this.ClaimAmount = claimAmount;
        this.status = status;
        this.BankingInfo = bankingInfo;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Date getClaimDate() {
        return ClaimDate;
    }

    public void setClaimDate(Date claimDate) {
        ClaimDate = claimDate;
    }

    public String getInsuredPerson() {
        return InsuredPerson;
    }

    public void setInsuredPerson(String insuredPerson) {
        InsuredPerson = insuredPerson;
    }

    public int getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(int cardNumber) {
        CardNumber = cardNumber;
    }

    public Date getExamDate() {
        return ExamDate;
    }

    public void setExamDate(Date examDate) {
        ExamDate = examDate;
    }

    public List<String> getDocuments() {
        return Documents;
    }

    public void setDocuments(List<String> documents) {
        Documents = documents;
    }

    public double getClaimAmount() {
        return ClaimAmount;
    }

    public void setClaimAmount(double claimAmount) {
        ClaimAmount = claimAmount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getBankingInfo() {
        return BankingInfo;
    }

    public void setBankingInfo(String bankingInfo) {
        BankingInfo = bankingInfo;
    }
    public static void addClaim(Claim newClaim) {
        claims.add(newClaim);
    }
    public long nextLong(Random rng, long n) {
        long bits, val;
        do {
            bits = (rng.nextLong() << 1) >>> 1;
            val = bits % n;
        } while (bits-val+(n-1) < 0L);
        return val;
    }
    @Override
    public String IDGenerator() {
        long number = nextLong(random, 9_000_000_000L) + 1_000_000_000L;
        return "f-" + number;
    }
    @Override
    public void addClaim(Scanner scanner) {
        System.out.println("Enter ID:");
        String ID = scanner.nextLine();

        System.out.println("Enter Claim Date (dd/MM/yyyy):");
        Date claimDate = null;
        try {
            claimDate = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("Enter Insured Person:");
        String insuredPerson = scanner.nextLine();

        long cardNumber;
        while (true) {
            System.out.println("Enter Card Number (10 digits):");
            cardNumber = scanner.nextLong();
            scanner.nextLine(); // consume newline left-over

            String cardNumberStr = Long.toString(cardNumber);
            if (cardNumberStr.length() == 10) {
                break;
            } else {
                System.out.println("Invalid Card Number. It should be exactly 10 digits.");
            }
        }

        System.out.println("Enter Exam Date (dd/MM/yyyy):");
        Date examDate = null;
        try {
            examDate = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("Enter Documents (comma separated):");
        List<String> documents = new ArrayList<>(Arrays.asList(scanner.nextLine().split(",")));

        // Format the documents
        for (int i = 0; i < documents.size(); i++) {
            String documentName = documents.get(i);
            documentName = ID + "_" + cardNumber + "_" + documentName + ".pdf";
            documents.set(i, documentName);
        }

        System.out.println("Enter Claim Amount:");
        double claimAmount = scanner.nextDouble();
        scanner.nextLine(); // consume newline left-over

        System.out.println("Enter Status (APPROVED, REJECTED, PENDING):");
        Status status = Status.valueOf(scanner.nextLine().toUpperCase());

        String bankId;
        while (true) {
            System.out.println("Enter the Bank ID (format b- followed by 7 digits):");
            bankId = scanner.nextLine();

            if (bankId.matches("b-\\d{7}")) {
                break;
            } else {
                System.out.println("Invalid Bank ID. It should be in the format b- followed by 7 digits.");
            }
        }
        System.out.println("Enter the Bank Name:");
        String bankName = scanner.nextLine();

        System.out.println("Enter the Account Number:");
        double accountNumber = scanner.nextDouble();
        scanner.nextLine(); // consume newline left-over

        // Create a new BankingInfo object
        BankingInfo bankid = new BankingInfo();
        String id = bankid.IDGenerator();
        BankingInfo newBank = new BankingInfo(id, bankName, accountNumber);

        // Save the BankingInfo object to a file
        LoadSaveData dataHandler = new LoadSaveData();
        dataHandler.saveBankingInfo(newBank, "BankingInfo.txt");

        // Convert the BankingInfo object to a string
        String bankingInfo = newBank.toString();

        // Create a new Claim object
        Claim newClaim = new Claim(ID, claimDate, insuredPerson, Math.toIntExact(cardNumber), examDate, documents, claimAmount, status, bankingInfo);

        // Add the new claim to the list of claims
        Claim.addClaim(newClaim);

        System.out.println("Claim added successfully with ID: " + ID);
    }
    @Override
    public void updateClaim(Scanner scanner) {
        System.out.println("Enter the ID of the claim you want to update:");
        String ID = scanner.nextLine();

        // Find the claim in the list of claims
        for (Claim claim : claims) {
            if (claim.getID().equals(ID)) {
                // If the claim is found, ask for the new details
                System.out.println("Enter new Claim Date (dd/MM/yyyy):");
                Date claimDate = null;
                try {
                    claimDate = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                System.out.println("Enter new Insured Person:");
                String insuredPerson = scanner.nextLine();

                System.out.println("Enter new Card Number:");
                int cardNumber = scanner.nextInt();
                scanner.nextLine(); // consume newline left-over

                System.out.println("Enter new Exam Date (dd/MM/yyyy):");
                Date examDate = null;
                try {
                    examDate = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                System.out.println("Enter new Documents (comma separated):");
                List<String> documents = new ArrayList<>(Arrays.asList(scanner.nextLine().split(",")));

                System.out.println("Enter new Claim Amount:");
                double claimAmount = scanner.nextDouble();
                scanner.nextLine(); // consume newline left-over

                System.out.println("Enter new Status (APPROVED, REJECTED, PENDING):");
                Status status = Status.valueOf(scanner.nextLine().toUpperCase());

                System.out.println("Enter the new Bank ID (format b- followed by 7 digits):");
                String newBankId = scanner.nextLine();

                // Validate the new bank ID
                while (!newBankId.matches("b-\\d{7}")) {
                    System.out.println("Invalid Bank ID. It should be in the format b- followed by 7 digits.");
                    newBankId = scanner.nextLine();
                }

                System.out.println("Enter the new Bank Name:");
                String bankName = scanner.nextLine();

                System.out.println("Enter the new Account Number:");
                double accountNumber = scanner.nextDouble();
                scanner.nextLine(); // consume newline left-over

                // Create a new BankingInfo object
                BankingInfo bankid = new BankingInfo();
                String id = bankid.IDGenerator();
                BankingInfo newBank = new BankingInfo(id, bankName, accountNumber);

                // Set the new bank ID
                newBank.setID(newBankId);

                // Convert the BankingInfo object to a string
                String bankingInfo = newBank.toString();

                // Update the claim with the new details
                LoadSaveData dataHandler = new LoadSaveData();
                dataHandler.saveBankingInfo(newBank, "BankingInfo.txt");

                claim.setClaimDate(claimDate);
                claim.setInsuredPerson(insuredPerson);
                claim.setCardNumber(cardNumber);
                claim.setExamDate(examDate);
                claim.setDocuments(documents);
                claim.setClaimAmount(claimAmount);
                claim.setStatus(status);
                claim.setBankingInfo(bankingInfo);
                System.out.println("Claim updated successfully with ID: " + ID);
                return;
            }
        }

        // If the claim is not found, inform the user
        System.out.println("Claim with ID: " + ID + " not found.");
    }
    @Override
    public void deleteClaim(Scanner scanner) {
        System.out.println("Enter the ID of the claim you want to delete:");
        String ID = scanner.nextLine();

        // Find the claim in the list of claims
        for (Iterator<Claim> iterator = claims.iterator(); iterator.hasNext();) {
            Claim claim = iterator.next();
            if (claim.getID().equals(ID)) {
                // If the claim is found, remove it from the list
                iterator.remove();
                System.out.println("Claim deleted successfully with ID: " + ID);
                return;
            }
        }

        // If the claim is not found, inform the user
        System.out.println("Claim with ID: " + ID + " not found.");
    }
    @Override
    public void getOneClaim(Scanner scanner) {
        System.out.println("Enter the ID of the claim you want to retrieve:");
        String ID = scanner.nextLine();

        // Load the bank information from a file
        LoadSaveData loadSaveData = new LoadSaveData();
        List<BankingInfo> banks = loadSaveData.loadBankingInfo("bankingInfo.txt");

        // Find the claim in the list of claims
        for (Claim claim : claims) {
            if (claim.getID().equals(ID)) {
                // If the claim is found, print its details
                System.out.println("Claim ID: " + claim.getID());
                System.out.println("Claim Date: " + claim.getClaimDate());
                System.out.println("Insured Person: " + claim.getInsuredPerson());
                System.out.println("Card Number: " + claim.getCardNumber());
                System.out.println("Exam Date: " + claim.getExamDate());
                System.out.println("Documents: " + claim.getDocuments());
                System.out.println("Claim Amount: " + claim.getClaimAmount());
                System.out.println("Status: " + claim.getStatus());
                System.out.println("Banking Info: " + claim.getBankingInfo());
                return;
            }
        }

        // If the claim is not found, inform the user
        System.out.println("Claim with ID: " + ID + " not found.");
    }
    @Override
    public void getAllClaims() {
        // If there are no claims, inform the user
        if (claims.isEmpty()) {
            System.out.println("No claims found.");
            return;
        }

        // Iterate over the list of claims
        for (Claim claim : claims) {
            System.out.println(claim.toString());
        }
    }

    @Override
    public String toString() {
        return  '{' +
                "ID=" + ID + '\'' +
                ", ClaimDate=" + ClaimDate +
                ", InsuredPerson=" + InsuredPerson + '\'' +
                ", CardNumber=" + CardNumber +
                ", ExamDate=" + ExamDate +
                ", Documents=" + Documents +
                ", ClaimAmount=" + ClaimAmount +
                ", status=" + status +
                ", BankingInfo=" + BankingInfo + '\'' +
                '}';
    }
}
