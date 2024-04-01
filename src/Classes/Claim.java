package Classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import Enum.Status;
import Interface.ClaimProcessManager;

public class Claim implements ClaimProcessManager {
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

        System.out.println("Enter Card Number:");
        int cardNumber = scanner.nextInt();
        scanner.nextLine(); // consume newline left-over

        System.out.println("Enter Exam Date (dd/MM/yyyy):");
        Date examDate = null;
        try {
            examDate = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("Enter Documents (comma separated):");
        List<String> documents = new ArrayList<>(Arrays.asList(scanner.nextLine().split(",")));

        System.out.println("Enter Claim Amount:");
        double claimAmount = scanner.nextDouble();
        scanner.nextLine(); // consume newline left-over

        System.out.println("Enter Status (APPROVED, REJECTED, PENDING):");
        Status status = Status.valueOf(scanner.nextLine().toUpperCase());

        System.out.println("Enter Banking Info:");
        String bankingInfo = scanner.nextLine();

        // Create a new Claim object
        Claim newClaim = new Claim(ID, claimDate, insuredPerson, cardNumber, examDate, documents, claimAmount, status, bankingInfo);

        // Add the new claim to the list of claims
        // Assuming there's a static method in the Claim class to add a claim
        ClaimManager.addClaim(newClaim);

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

                System.out.println("Enter new Banking Info:");
                String bankingInfo = scanner.nextLine();

                // Update the claim with the new details
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
            // Print the details of each claim
            System.out.println("Claim ID: " + claim.getID());
            System.out.println("Claim Date: " + claim.getClaimDate());
            System.out.println("Insured Person: " + claim.getInsuredPerson());
            System.out.println("Card Number: " + claim.getCardNumber());
            System.out.println("Exam Date: " + claim.getExamDate());
            System.out.println("Documents: " + claim.getDocuments());
            System.out.println("Claim Amount: " + claim.getClaimAmount());
            System.out.println("Status: " + claim.getStatus());
            System.out.println("Banking Info: " + claim.getBankingInfo());
            System.out.println("------------------------");
        }
    }

}
