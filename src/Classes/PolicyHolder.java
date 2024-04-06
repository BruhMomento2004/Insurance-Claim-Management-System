package Classes;

/**
 * @author <Nguyen Thanh Tung - s3979489>
 * Reference: https://github.com/VINAYKUMARKUNDER/Insurance-Management-System.git and
 * https://youtu.be/xNeOHmqNVus?si=4L5anBRVpkQJviVH
 */

import java.util.*;
import java.util.stream.Collectors;

public class PolicyHolder extends Customer{
    private List<String> dependents;
    private static List<PolicyHolder> policyHolders = new ArrayList<>();
    private static Random random = new Random();
    public PolicyHolder() {
    }
    public PolicyHolder(String id, String fullName, long insuranceCard, List<String> claims, List<String> dependents) {
        super(id, fullName, insuranceCard, claims);
        this.dependents = dependents != null ? dependents : new ArrayList<>();
    }
    public List<String> getDependents() {
        return dependents;
    }

    public void setDependents(List<String> dependents) {
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

        long insuranceCard;
        while (true) {
            System.out.println("Enter Insurance Card number (10 digits):");
            insuranceCard = scanner.nextLong();
            scanner.nextLine();

            String insuranceCardStr = Long.toString(insuranceCard);
            if (insuranceCardStr.length() == 10) {
                break;
            } else {
                System.out.println("Invalid Insurance Card number. It should be exactly 10 digits.");
            }
        }

        // Ask for the claims
        System.out.println("Enter the claims (in the format of f-1234567890_f-9283918294):");
        String claimsStr = scanner.nextLine();
        List<String> claims = Arrays.asList(claimsStr.split(","));

        // Generate a unique ID for the customer
        String id = "c-" + IDGenerator();

        // Create a new PolicyHolder object
        PolicyHolder newPolicyHolder = new PolicyHolder(id, fullName, insuranceCard, claims, new ArrayList<>());

        // Add the new policyholder to the list of policyholders
        PolicyHolder.addPolicyHolder(newPolicyHolder);

        // Ask for the dependents
        System.out.println("Enter the dependents (in the format c-1234782_c-8293721):");
        String dependentsStr = scanner.nextLine();
        List<String> dependents = Arrays.asList(dependentsStr.split("_"));

        // Filter out any null values from the list of dependents
        dependents = dependents.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        // Add the dependents to the list of dependents of the policyholder
        newPolicyHolder.setDependents(dependents);

        // Save the new policyholder to the text file
        LoadSaveData loadSaveData = new LoadSaveData();
        loadSaveData.savePolicyHolder(newPolicyHolder);

        System.out.println("Policy Holder created successfully");
    }
    @Override
    public void updateCustomer(Scanner scanner) {
        System.out.println("Enter the ID of the customer you want to update:");
        String id = scanner.nextLine();

        // Create an instance of LoadSaveData
        LoadSaveData loadSaveData = new LoadSaveData();

        // Load the policyholders from the text file
        List<PolicyHolder> policyHolders = loadSaveData.loadPolicyHolder();

        // Search for the customer in the list of policyholders
        for (PolicyHolder policyHolder : policyHolders) {
            if (policyHolder.getId().equals(id)) {
                System.out.println("Enter new Full Name (or leave blank to keep the current name):");
                String fullName = scanner.nextLine();
                if (!fullName.isEmpty() && !fullName.equalsIgnoreCase("skip")) {
                    policyHolder.setFullName(fullName);
                }

                while (true) {
                    System.out.println("Enter new Insurance Card number (or leave blank to keep the current number):");
                    String insuranceCardStr = scanner.nextLine();
                    if (insuranceCardStr.isEmpty() || insuranceCardStr.equalsIgnoreCase("skip")) {
                        break;
                    } else if (insuranceCardStr.length() == 10) {
                        long insuranceCard = Long.parseLong(insuranceCardStr);
                        policyHolder.setInsuranceCard(insuranceCard);
                        break;
                    } else {
                        System.out.println("Invalid Insurance Card number. It should be exactly 10 digits.");
                    }
                }

                System.out.println("Enter new Claims (f-1234567890_f-xxxxxxxxxx) (or leave blank to keep the current claims):");
                String claimsStr = scanner.nextLine();
                if (!claimsStr.isEmpty() && !claimsStr.equalsIgnoreCase("skip")) {
                    List<String> claims = Arrays.asList(claimsStr.split(","));
                    policyHolder.setClaims(claims);
                }

                System.out.println("Enter new Dependents (c-1234567_c-xxxxxxxx) (or leave blank to keep the current dependents):");
                String dependentsStr = scanner.nextLine();
                if (!dependentsStr.isEmpty() && !dependentsStr.equalsIgnoreCase("skip")) {
                    List<String> dependents = Arrays.asList(dependentsStr.split(","));
                    policyHolder.setDependents(dependents);
                }

                // Save the updated policyholder back to the text file
                loadSaveData.updatePolicyHolder(policyHolders);

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

        // Create an instance of LoadSaveData
        LoadSaveData loadSaveData = new LoadSaveData();

        // Load the policyholders from the text file
        List<PolicyHolder> policyHolders = loadSaveData.loadPolicyHolder();

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
    public void readAllCustomers() {
        LoadSaveData loadSaveData = new LoadSaveData();

        // Load the policyholders from the file
        List<PolicyHolder> policyHolders = loadSaveData.loadPolicyHolder();

        // If there are no policyholders, inform the user
        if (policyHolders.isEmpty()) {
            System.out.println("No policy holders found.");
            return;
        }

        // Sort the policyholders based on their IDs
        Collections.sort(policyHolders, new Comparator<PolicyHolder>() {
            @Override
            public int compare(PolicyHolder p1, PolicyHolder p2) {
                return p1.getId().compareTo(p2.getId());
            }
        });

        for (PolicyHolder policyHolder : policyHolders) {
            System.out.println(policyHolder.toString());
        }
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
        return getClaims();
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
