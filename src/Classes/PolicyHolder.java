package Classes;

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
    public void createCustomer(Scanner scanner){};
    @Override
    public void updateCustomer(Scanner scanner){};
    @Override
    public  void readCustomer(Scanner scanner){};

}
