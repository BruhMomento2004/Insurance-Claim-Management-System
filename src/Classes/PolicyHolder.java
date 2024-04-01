package Classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PolicyHolder extends Customer{
    private List<Dependent> dependents;
    public PolicyHolder() {
    }
    public PolicyHolder(String id, String fullName, int insuranceCard, List<String> claims, List<Dependent> dependents) {
        super(id, fullName, insuranceCard, claims);
        this.dependents = dependents;
    }
    public List<Dependent> getDependents() {
        return dependents;
    }

    public void setDependents(List<Dependent> dependents) {
        this.dependents = dependents;
    }
    @Override
    public void createCustomer(Scanner scanner) {
        System.out.println("Enter Full Name:");
        String fullName = scanner.nextLine();

        System.out.println("Enter Insurance Card number:");
        int insuranceCard = scanner.nextInt();
        scanner.nextLine(); // consume newline left-over

        // Generate a unique ID for the customer
        String id = IDGenerator();

        // Create a new PolicyHolder object
        PolicyHolder newPolicyHolder = new PolicyHolder(id, fullName, insuranceCard, new ArrayList<>(), new ArrayList<>());

        // Add the new policyholder to the list of policyholders
        super.addPolicyHolder(newPolicyHolder);

        System.out.println("Policy Holder created successfully with ID: " + id);
    }
    @Override
    public void updateCustomer(Scanner scanner) {
        System.out.println("Enter the ID of the customer you want to update:");
        String id = scanner.nextLine();

        // Search for the customer in the list of policyholders
        for (PolicyHolder policyHolder : getPolicyHolders()) {
            if (policyHolder.getId().equals(id)) {
                updateCustomerDetails(scanner, policyHolder);
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

        // Search for the customer in the list of policyholders
        for (PolicyHolder policyHolder : getPolicyHolders()) {
            if (policyHolder.getId().equals(id)) {
                printCustomerDetails(policyHolder);
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
