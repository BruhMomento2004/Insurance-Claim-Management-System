package Classes;

/**
 * @author <Nguyen Thanh Tung - s3979489>
 * Reference: https://github.com/VINAYKUMARKUNDER/Insurance-Management-System.git and
 * https://youtu.be/xNeOHmqNVus?si=4L5anBRVpkQJviVH
 */

import Interface.generateID;

import java.util.*;

public abstract class Customer implements generateID {
    private String id;
    private String FullName;
    private long InsuranceCard;
    private List<String> Claims;
    private static Random random = new Random();


    public Customer() {
    }
    public Customer(String id, String fullName, long insuranceCard, List<String> claims) {
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

    public long getInsuranceCard() {
        return InsuranceCard;
    }

    public void setInsuranceCard(long insuranceCard) {
        InsuranceCard = insuranceCard;
    }

    public List<String> getClaims() {
        return Claims;
    }

    public void setClaims(List<String> claims) {
        Claims = claims;
    }

    @Override
    public String IDGenerator() {
        int number = random.nextInt(9_000_000) + 1_000_000;
        return "c-" + number;
    }

    public abstract void createCustomer(Scanner scanner);
    public abstract void updateCustomer(Scanner scanner);
    public abstract void readCustomer(Scanner scanner);
    public abstract void readAllCustomers();

    public abstract String getCustomerID();

    public abstract String getCustomerFullName();

    public abstract long getCustomerInsuranceCard();

    public abstract List<String> getCustomerClaims();


    //CRU no D
}
