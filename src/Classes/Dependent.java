package Classes;

import Interface.generateID;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Dependent extends Customer implements generateID {
    private String PolicyHolderId;
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

    @Override
    public void createCustomer(Scanner scanner){};
    @Override
    public void updateCustomer(Scanner scanner){};
    @Override
    public  void readCustomer(Scanner scanner){};
    @Override
    public String IDGenerator() {
        int number = random.nextInt(9_000_000) + 1_000_000;
        return "c-" + number;
    }
}
