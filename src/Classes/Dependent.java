package Classes;

import Interface.generateID;

import java.util.*;

public class Dependent extends Customer implements generateID {
    private String PolicyHolderId;
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

    @Override
    public void createCustomer(Scanner scanner) {
        System.out.println("Enter Full Name:");
        String fullName = scanner.nextLine();

        System.out.println("Enter Insurance Card number:");
        int insuranceCard = scanner.nextInt();
        scanner.nextLine(); // consume newline left-over

        System.out.println("Enter Policy Holder's ID:");
        String policyHolderId = scanner.nextLine();

        // Generate a unique ID for the customer
        String id = IDGenerator();

        // Create a new Dependent object
        Dependent newDependent = new Dependent(id, fullName, insuranceCard, new ArrayList<>(), policyHolderId);

        // Add the new dependent to the list of dependents
        super.addDependent(newDependent);

        System.out.println("Dependent created successfully with ID: " + id);
    }
    @Override
    public void updateCustomer(Scanner scanner) {
        System.out.println("Enter the ID of the customer you want to update:");
        String id = scanner.nextLine();

        // Search for the customer in the list of dependents
        for (Dependent dependent : getDependents()) {
            if (dependent.getId().equals(id)) {
                updateCustomerDetails(scanner, dependent);
                return;
            }
        }

        System.out.println("Customer with ID: " + id + " not found.");
    }

    private void updateCustomerDetails(Scanner scanner, Customer customer) {
        System.out.println("Enter new Full Name:");
        String fullName = scanner.nextLine();

        System.out.println("Enter new Insurance Card number:");
        int insuranceCard = scanner.nextInt();
        scanner.nextLine(); // consume newline left-over

        System.out.println("Enter new Claims (comma separated):");
        String claimsStr = scanner.nextLine();
        List<String> claims = Arrays.asList(claimsStr.split(","));

        // Update the customer details
        customer.setFullName(fullName);
        customer.setInsuranceCard(insuranceCard);
        customer.setClaims(claims);

        System.out.println("Customer updated successfully.");
    }
    @Override
    public void readCustomer(Scanner scanner) {
        System.out.println("Enter the ID of the customer you want to read:");
        String id = scanner.nextLine();

        // Search for the customer in the list of dependents
        for (Dependent dependent : getDependents()) {
            if (dependent.getId().equals(id)) {
                printCustomerDetails(dependent);
                return;
            }
        }

        System.out.println("Customer with ID: " + id + " not found.");
    }

    private void printCustomerDetails(Customer customer) {
        System.out.println("Full Name: " + customer.getFullName());
        System.out.println("Insurance Card number: " + customer.getInsuranceCard());
        System.out.println("Claims: " + customer.getClaims());
    }
}
