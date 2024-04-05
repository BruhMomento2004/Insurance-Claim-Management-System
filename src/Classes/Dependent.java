package Classes;

/**
 * @author <Nguyen Thanh Tung - s3979489>
 */

import Interface.generateID;

import java.util.*;

public class Dependent extends Customer implements generateID {
    private String PolicyHolderId;
    private static List<Dependent> dependents = new ArrayList<>();
    private List<String> customerClaims; // This is a list of claims represented by their IDs
    private static Random random = new Random();
    public Dependent() {
    }
    public Dependent(String id, String fullName, long insuranceCard, List<String> claims, String policyHolderId) {
        super(id, fullName, insuranceCard, claims);
        this.PolicyHolderId = policyHolderId;
        this.customerClaims = claims != null ? claims : new ArrayList<>();
    }

    public String getPolicyHolderId() {
        return PolicyHolderId;
    }
    public void setPolicyHolderId(String policyHolderId) {
        this.PolicyHolderId = policyHolderId;
    }
    public static List<Dependent> getDependents() {
        return dependents;
    }
    public static void addDependent(Dependent newDependent){
        dependents.add(newDependent);
    }

    @Override
    public String IDGenerator() {
        int number = random.nextInt(9_000_000) + 1_000_000;
        return "c-" + number;
    }
    @Override
    public void createCustomer(Scanner scanner) {
        System.out.println("Enter Full Name:");
        String fullName = scanner.nextLine();

        long insuranceCard;
        while (true) {
            System.out.println("Enter Insurance Card number (10 digits):");
            insuranceCard = scanner.nextLong();
            scanner.nextLine(); // consume newline left-over

            String insuranceCardStr = Long.toString(insuranceCard);
            if (insuranceCardStr.length() == 10) {
                break;
            } else {
                System.out.println("Invalid Insurance Card number. It should be exactly 10 digits.");
            }
        }

        System.out.println("Enter Policy Holder's ID (c-xxxxxxx):");
        String policyHolderId = scanner.nextLine();

        // Regular expression for the required ID format
        String idFormat = "c-\\d{7}";

        while (!policyHolderId.matches(idFormat)) {
            System.out.println("Invalid ID format. Please enter the ID in the format 'c-xxxxxxx':");
            policyHolderId = scanner.nextLine();
        }

        System.out.println("Enter Claims (the IDs of the claims in the correct format, for multiple claim connect them with _ in between the claims IDs):");
        String claimsStr = scanner.nextLine();
        List<String> claims = Arrays.asList(claimsStr.split(","));

        // Generate a unique ID for the customer
        String id = IDGenerator();

        // Create a new Dependent object
        Dependent newDependent = new Dependent(id, fullName, insuranceCard, claims, policyHolderId);
        // Add the new dependent to the list of dependents
        Dependent.addDependent(newDependent);

        // Save the new dependent to the file
        LoadSaveData loadSaveData = new LoadSaveData();
        loadSaveData.saveDependent(newDependent);

        System.out.println("Dependent created successfully");
    }
    @Override
    public void updateCustomer(Scanner scanner) {
        System.out.println("Enter the ID of the customer you want to update:");
        String id = scanner.nextLine();

        // Create an instance of LoadSaveData
        LoadSaveData loadSaveData = new LoadSaveData();

        // Load the customers from the file
        List<Dependent> dependents = loadSaveData.loadDependent();

        // Search for the customer in the list of dependents
        for (Dependent dependent : dependents) {
            if (dependent.getId().equals(id)) {
                System.out.println("Enter new Full Name:");
                String fullName = scanner.nextLine();

                System.out.println("Enter new Insurance Card number:");
                long insuranceCard = scanner.nextLong();
                scanner.nextLine(); // consume newline left-over
                String insuranceCardStr = Long.toString(insuranceCard);
                while (insuranceCardStr.length() != 10) {
                    System.out.println("Invalid Insurance Card number. It should be exactly 10 digits.");
                    System.out.println("Enter new Insurance Card number:");
                    insuranceCard = scanner.nextLong();
                    scanner.nextLine(); // consume newline left-over
                    insuranceCardStr = Long.toString(insuranceCard);
                }

                System.out.println("Enter new Claims (comma separated):");
                String claimsStr = scanner.nextLine();
                List<String> claims = Arrays.asList(claimsStr.split(","));

                System.out.println("Enter new Policy Holder's ID (c-xxxxxxx):");
                String policyHolderId = scanner.nextLine();

                // Regular expression for the required ID format
                String idFormat = "c-\\d{7}";

                while (!policyHolderId.matches(idFormat)) {
                    System.out.println("Invalid ID format. Please enter the ID in the format 'c-xxxxxxx':");
                    policyHolderId = scanner.nextLine();
                }

                // Update the customer details
                dependent.setFullName(fullName);
                dependent.setInsuranceCard(insuranceCard);
                dependent.setClaims(claims);
                dependent.setPolicyHolderId(policyHolderId);

                // Save the updated dependent back to the file
                loadSaveData.updateDependent(dependents);

                System.out.println("Dependent updated successfully.");
                return;
            }
        }

        System.out.println("Dependent with ID: " + id + " not found.");
    }
    @Override
    public void readCustomer(Scanner scanner) {
        System.out.println("Enter the ID of the dependent you want to read:");
        String id = scanner.nextLine();

        // Create an instance of LoadSaveData
        LoadSaveData loadSaveData = new LoadSaveData();

        // Load the customers from the file
        List<Dependent> dependents = loadSaveData.loadDependent();

        // Search for the customer in the list of dependents
        for (Dependent dependent : dependents) {
            if (dependent.getId().equals(id)) {
                // Print the customer details
                System.out.println("Full Name: " + dependent.getFullName());
                System.out.println("Insurance Card number: " + dependent.getInsuranceCard());
                System.out.println("Claims: " + dependent.getClaims());
                System.out.println("Policy Holder's ID: " + dependent.getPolicyHolderId());
                return;
            }
        }

        System.out.println("Dependent with ID: " + id + " not found.");
    }

    @Override
    public String getCustomerID() {
        return getId();
    }

    @Override
    public String getCustomerFullName() {
        return getFullName();
    }

    @Override
    public long getCustomerInsuranceCard() {
        return getInsuranceCard();
    }

    @Override
    public List<String> getCustomerClaims() {
        return customerClaims != null ? customerClaims : new ArrayList<>();
    }

    @Override
    public String toString() {
        return '{' + "id=" + getId() +
                ", FullName=" + getFullName() +
                ", InsuranceCard=" + getInsuranceCard() +
                ", Claims=" + getClaims() +
                "PolicyHolderId=" + PolicyHolderId +
                '}';
    }
}
