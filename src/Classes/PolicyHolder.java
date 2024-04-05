package Classes;

/**
 * @author <Nguyen Thanh Tung - s3979489>
 */

import java.util.*;

public class PolicyHolder extends Customer{
    private List<Dependent> dependents;
    private static List<PolicyHolder> policyHolders = new ArrayList<>();
    private static Random random = new Random();
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
    public static List<PolicyHolder> getPolicyHolders() {
        return policyHolders;
    }
    public static void addPolicyHolder(PolicyHolder newPolicyHolder){
        policyHolders.add(newPolicyHolder);
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

        // Generate a unique ID for the customer
        String id = IDGenerator();

        // Create a new PolicyHolder object
        PolicyHolder newPolicyHolder = new PolicyHolder(id, fullName, insuranceCard, new ArrayList<>(), new ArrayList<>());

        // Add the new policyholder to the list of policyholders
        PolicyHolder.addPolicyHolder(newPolicyHolder);

        System.out.println("Policy Holder created successfully with ID: " + id);
    }
    @Override
    public void updateCustomer(Scanner scanner) {
        System.out.println("Enter the ID of the customer you want to update:");
        String id = scanner.nextLine();

        // Search for the customer in the list of policyholders
        for (PolicyHolder policyHolder : policyHolders) {
            if (policyHolder.getId().equals(id)) {
                System.out.println("Enter new Full Name:");
                String fullName = scanner.nextLine();

                System.out.println("Enter new Insurance Card number:");
                int insuranceCard = scanner.nextInt();
                scanner.nextLine(); // consume newline left-over

                System.out.println("Enter new Claims (comma separated):");
                String claimsStr = scanner.nextLine();
                List<String> claims = Arrays.asList(claimsStr.split(","));

                // Update the customer details
                policyHolder.setFullName(fullName);
                policyHolder.setInsuranceCard(insuranceCard);
                policyHolder.setClaims(claims);

                System.out.println("Customer updated successfully.");
                return;
            }
        }

        System.out.println("Customer with ID: " + id + " not found.");
    }
    @Override
    public void readCustomer(Scanner scanner) {
        System.out.println("Enter the ID of the Policy Holder you want to read:");
        String id = scanner.nextLine();

        // Search for the customer in the list of policyholders
        for (PolicyHolder policyHolder : policyHolders) {
            if (policyHolder.getId().equals(id)) {
                // Print the customer details
                System.out.println("Full Name: " + policyHolder.getFullName());
                System.out.println("Insurance Card number: " + policyHolder.getInsuranceCard());
                System.out.println("Claims: " + policyHolder.getClaims());
                System.out.println("Dependents: " + policyHolder.getDependents());
                return;
            }
        }

        System.out.println("Customer with ID: " + id + " not found.");
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
        return null;
    }

    @Override
    public String toString() {
        return '{' + "id=" + getId() +
                ", FullName=" + getFullName() +
                ", InsuranceCard=" + getInsuranceCard() +
                ", Claims=" + getClaims() +
                "Dependents=" + dependents +
                '}';
    }
}
