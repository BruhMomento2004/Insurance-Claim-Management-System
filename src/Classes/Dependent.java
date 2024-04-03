package Classes;

/**
 * @author <Nguyen Thanh Tung - s3979489>
 */

import Interface.generateID;

import java.util.*;

public class Dependent extends Customer implements generateID {
    private String PolicyHolderId;
    private static List<Dependent> dependents = new ArrayList<>();
    private static Random random = new Random();
    public Dependent() {
    }
    public Dependent(String id, String fullName, int insuranceCard, List<String> claims, String policyHolderId) {
        super(id, fullName, insuranceCard, claims);
        this.PolicyHolderId = policyHolderId;
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

        int insuranceCard;
        while (true) {
            System.out.println("Enter Insurance Card number (10 digits):");
            insuranceCard = scanner.nextInt();
            scanner.nextLine(); // consume newline left-over

            String insuranceCardStr = Integer.toString(insuranceCard);
            if (insuranceCardStr.length() == 10) {
                break;
            } else {
                System.out.println("Invalid Insurance Card number. It should be exactly 10 digits.");
            }
        }

        System.out.println("Enter Policy Holder's ID:");
        String policyHolderId = scanner.nextLine();

        // Generate a unique ID for the customer
        String id = IDGenerator();

        // Create a new Dependent object
        Dependent newDependent = new Dependent(id, fullName, insuranceCard, new ArrayList<>(), policyHolderId);

        // Add the new dependent to the list of dependents
        Dependent.addDependent(newDependent);

        System.out.println("Dependent created successfully with ID: " + id);
    }
    @Override
    public void updateCustomer(Scanner scanner) {
        System.out.println("Enter the ID of the customer you want to update:");
        String id = scanner.nextLine();

        // Search for the customer in the list of dependents
        for (Dependent dependent : dependents) {
            if (dependent.getId().equals(id)) {
                System.out.println("Enter new Full Name:");
                String fullName = scanner.nextLine();

                System.out.println("Enter new Insurance Card number:");
                int insuranceCard = scanner.nextInt();
                scanner.nextLine(); // consume newline left-over

                System.out.println("Enter new Claims (comma separated):");
                String claimsStr = scanner.nextLine();
                List<String> claims = Arrays.asList(claimsStr.split(","));

                // Update the customer details
                dependent.setFullName(fullName);
                dependent.setInsuranceCard(insuranceCard);
                dependent.setClaims(claims);

                System.out.println("Customer updated successfully.");
                return;
            }
        }

        System.out.println("Customer with ID: " + id + " not found.");
    }
    @Override
    public void readCustomer(Scanner scanner) {
        System.out.println("Enter the ID of the dependent you want to read:");
        String id = scanner.nextLine();

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

        System.out.println("Customer with ID: " + id + " not found.");
    }
    @Override
    public String toString() {
        return '{' + "id=" + getId() + '\'' +
                ", FullName=" + getFullName() + '\'' +
                ", InsuranceCard=" + getInsuranceCard() +
                ", Claims=" + getClaims() +
                "PolicyHolderId=" + PolicyHolderId + '\'' +
                '}';
    }
}
