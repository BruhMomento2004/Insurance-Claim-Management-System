package Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Customer {
    private String id;
    private String FullName;
    private int InsuranceCard;
    private List<String> Claims;
    private static List<Dependent> dependents = new ArrayList<>(); // This is a list of dependents
    private static List<PolicyHolder> policyHolders = new ArrayList<>(); // This is a list of policy holders

    public Customer() {
    }

    public Customer(String id, String fullName, int insuranceCard, List<String> claims) {
        this.id = id;
        this.FullName = fullName;
        this.InsuranceCard = insuranceCard;
        this.Claims = claims;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public int getInsuranceCard() {
        return InsuranceCard;
    }

    public void setInsuranceCard(int insuranceCard) {
        InsuranceCard = insuranceCard;
    }

    public List<String> getClaims() {
        return Claims;
    }

    public void setClaims(List<String> claims) {
        Claims = claims;
    }

    public static void readAllCustomers() {
        System.out.println("Dependents:");
        for (Dependent dependent : dependents) {
            System.out.println("ID: " + dependent.getId());
            System.out.println("Full Name: " + dependent.getFullName());
            System.out.println("Insurance Card: " + dependent.getInsuranceCard());
            System.out.println("Claims: " + dependent.getClaims());
            System.out.println(" ");
        }

        System.out.println("Policy Holders:");
        for (PolicyHolder policyHolder : policyHolders) {
            System.out.println("ID: " + policyHolder.getId());
            System.out.println("Full Name: " + policyHolder.getFullName());
            System.out.println("Insurance Card: " + policyHolder.getInsuranceCard());
            System.out.println("Claims: " + policyHolder.getClaims());
            System.out.println(" ");
        }
    }

    public abstract void createCustomer(Scanner scanner);
    public abstract void updateCustomer(Scanner scanner);
    public abstract void readCustomer(Scanner scanner);

    //CRU no D
}
