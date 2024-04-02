package Classes;

/**
 * @author <Nguyen Thanh Tung - s3979489>
 */

import Interface.generateID;

import java.util.*;

public abstract class Customer implements generateID {
    private String id;
    private String FullName;
    private int InsuranceCard;
    private List<String> Claims;
    private static Random random = new Random();


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
        System.out.println("All policy holders");
        for (PolicyHolder policyHolder : PolicyHolder.getPolicyHolders()) {
            System.out.println(policyHolder);
        }
        System.out.println("All dependents");
        for (Dependent dependent : Dependent.getDependents()) {
            System.out.println(dependent);
        }
    }
    @Override
    public String IDGenerator() {
        int number = random.nextInt(9_000_000) + 1_000_000;
        return "c-" + number;
    }

    public abstract void createCustomer(Scanner scanner);
    public abstract void updateCustomer(Scanner scanner);
    public abstract void readCustomer(Scanner scanner);


    //CRU no D
}
