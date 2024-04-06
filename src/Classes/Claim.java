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
    private long CardNumber;
    private Date ExamDate;
    private List<String> Documents;
    private double ClaimAmount;
    private Status status;
    private String BankingInfo;
    private static List<Claim> claims = new ArrayList<>();
    private static Random random = new Random();


    public Claim() {
    }
    public Claim(String ID, Date claimDate, String insuredPerson, long cardNumber, Date examDate, List<String> documents, double claimAmount, Status status, String bankingInfo) {
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

    public long getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(long cardNumber) {
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
        String ID = IDGenerator();

        System.out.println("Enter Claim Date (yyyy-MM-dd):");
        Date claimDate = null;
        try {
            claimDate = new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());
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

        System.out.println("Enter Exam Date (yyyy-MM-dd):");
        Date examDate = null;
        try {
            examDate = new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("Enter Documents name:");
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

        System.out.println("Enter Status (NEW, PROCESSING, DONE):");
        Status status = Status.valueOf(scanner.nextLine().toUpperCase());

        System.out.println("Enter Bank Info (format: b-xxxxxxx - BankName - AccountNumber):");
        String bankingInfo = scanner.nextLine();

        // Regular expression to match the required format
        String regex = "b-\\d{7} - .+ - \\d+(\\.\\d+)?";

        while (!bankingInfo.matches(regex)) {
            System.out.println("Invalid input. Please enter in the format: b-xxxxxxx - BankName - AccountNumber");
            bankingInfo = scanner.nextLine();
        }

        // Create a new Claim object
        Claim newClaim = new Claim(ID, claimDate, insuredPerson, cardNumber, examDate, documents, claimAmount, status, bankingInfo);

        // Add the new claim to the list of claims
        Claim.addClaim(newClaim);

        LoadSaveData loadSaveData = new LoadSaveData();
        loadSaveData.saveClaim(newClaim);

        System.out.println("Claim added successfully with ID: " + ID);
    }
    @Override
    public void updateClaim(Scanner scanner) {
        System.out.println("Enter the ID of the claim you want to update (f-xxxxxxxxxx):");
        String ID = scanner.nextLine();

        // Create an instance of LoadSaveData
        LoadSaveData loadSaveData = new LoadSaveData();

        // Load the claims from the file
        List<Claim> claims = loadSaveData.loadClaim();

        // Find the claim in the list of claims
        for (Claim claim : claims) {
            if (claim.getID().equals(ID)) {
                // If the claim is found, ask for the new details
                System.out.println("Enter new Claim Date (yyyy-MM-dd) or type 'skip' to skip:");
                String claimDateStr = scanner.nextLine();
                if (!claimDateStr.isEmpty() && !claimDateStr.equalsIgnoreCase("skip")) {
                    try {
                        Date claimDate = new SimpleDateFormat("yyyy-MM-dd").parse(claimDateStr);
                        claim.setClaimDate(claimDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("Enter new Insured Person or type 'skip' to skip:");
                String insuredPerson = scanner.nextLine();
                if (!insuredPerson.isEmpty() && !insuredPerson.equalsIgnoreCase("skip")) {
                    claim.setInsuredPerson(insuredPerson);
                }

                System.out.println("Enter new Card Number or type 'skip' to skip:");
                String cardNumberStr = scanner.nextLine();
                if (!cardNumberStr.isEmpty() && !cardNumberStr.equalsIgnoreCase("skip")) {
                    long cardNumber = Long.parseLong(cardNumberStr);
                    claim.setCardNumber(cardNumber);
                }

                System.out.println("Enter new Exam Date (yyyy-MM-dd) or type 'skip' to skip:");
                String examDateStr = scanner.nextLine();
                if (!examDateStr.isEmpty() && !examDateStr.equalsIgnoreCase("skip")) {
                    try {
                        Date examDate = new SimpleDateFormat("yyyy-MM-dd").parse(examDateStr);
                        claim.setExamDate(examDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("Enter new Documents name or type 'skip' to skip:");
                String documentsStr = scanner.nextLine();
                if (!documentsStr.isEmpty() && !documentsStr.equalsIgnoreCase("skip")) {
                    List<String> documents = new ArrayList<>(Arrays.asList(documentsStr.split(",")));
                    claim.setDocuments(documents);
                }

                System.out.println("Enter new Claim Amount or type 'skip' to skip:");
                String claimAmountStr = scanner.nextLine();
                if (!claimAmountStr.isEmpty() && !claimAmountStr.equalsIgnoreCase("skip")) {
                    double claimAmount = Double.parseDouble(claimAmountStr);
                    claim.setClaimAmount(claimAmount);
                }

                System.out.println("Enter new Status (NEW, PROCESSING, DONE) or type 'skip' to skip:");
                String statusStr = scanner.nextLine();
                if (!statusStr.isEmpty() && !statusStr.equalsIgnoreCase("skip")) {
                    Status status = Status.valueOf(statusStr.toUpperCase());
                    claim.setStatus(status);
                }

                System.out.println("Enter Bank Info (format: b-xxxxxxx - BankName - AccountNumber) or type 'skip' to skip:");
                String bankingInfo = scanner.nextLine();
                if (!bankingInfo.isEmpty() && !bankingInfo.equalsIgnoreCase("skip")) {
                    claim.setBankingInfo(bankingInfo);
                }

                // Save the updated claim to the file
                loadSaveData.updateClaims(claims);

                System.out.println("Claim updated successfully");
                return;
            }
        }

        // If the claim is not found, inform the user
        System.out.println("Claim with ID: " + ID + " not found.");
    }
    @Override
    public void deleteClaim(Scanner scanner) {
        System.out.println("Enter the ID of the claim you want to delete (f-xxxxxxxxxx):");
        String ID = scanner.nextLine();

        // Create an instance of LoadSaveData
        LoadSaveData loadSaveData = new LoadSaveData();

        // Load the claims from the file
        List<Claim> claims = loadSaveData.loadClaim();

        // Find the claim in the list of claims
        for (Iterator<Claim> iterator = claims.iterator(); iterator.hasNext();) {
            Claim claim = iterator.next();
            if (claim.getID().equals(ID)) {
                // If the claim is found, remove it from the list
                iterator.remove();

                // Save the updated list of claims back to the file
                loadSaveData.updateClaims(claims);

                System.out.println("Claim deleted successfully with ID: " + ID);
                return;
            }
        }

        // If the claim is not found, inform the user
        System.out.println("Claim with ID: " + ID + " not found.");
    }
    @Override
    public void getOneClaim(Scanner scanner) {
        System.out.println("Enter the ID of the claim you want to retrieve (f-xxxxxxxxxx):");
        String ID = scanner.nextLine();

        // Create an instance of LoadSaveData
        LoadSaveData loadSaveData = new LoadSaveData();

        // Load the claims from the file
        List<Claim> claims = loadSaveData.loadClaim();

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
        // Create an instance of LoadSaveData
        LoadSaveData loadSaveData = new LoadSaveData();

        // Load the claims from the file
        List<Claim> claims = loadSaveData.loadClaim();

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
                "ID=" + ID +
                ", ClaimDate=" + ClaimDate +
                ", InsuredPerson=" + InsuredPerson +
                ", CardNumber=" + CardNumber +
                ", ExamDate=" + ExamDate +
                ", Documents=" + Documents +
                ", ClaimAmount=" + ClaimAmount +
                ", status=" + status +
                ", BankingInfo=" + BankingInfo +
                '}';
    }
}
