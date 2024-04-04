package Classes;

/**
 * @author <Nguyen Thanh Tung - s3979489>
 */

import Interface.generateID;

import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class InsuranceCard implements generateID {
    private String ID;
    private String CardHolder;
    private String PolicyOwner;
    private Date ExpiryDate;
    private static Random random = new Random();
    public InsuranceCard() {
    }
    public InsuranceCard(String ID, String cardHolder, String policyOwner, Date expiryDate) {
        this.ID = ID;
        this.CardHolder = cardHolder;
        this.PolicyOwner = policyOwner;
        this.ExpiryDate = expiryDate;
    }
    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCardHolder() {
        return CardHolder;
    }

    public void setCardHolder(String cardHolder) {
        CardHolder = cardHolder;
    }

    public String getPolicyOwner() {
        return PolicyOwner;
    }

    public void setPolicyOwner(String policyOwner) {
        PolicyOwner = policyOwner;
    }

    public Date getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        ExpiryDate = expiryDate;
    }
    private static List<InsuranceCard> cards = new ArrayList<>(); // This is a list of insurance cards

    public long nextLong(Random rng, long n) {
        long bits, val;
        do {
            bits = (rng.nextLong() << 1) >>> 1;
            val = bits % n;
        } while (bits-val+(n-1) < 0L);
        return val;
    }
    @Override
    public String IDGenerator() {
        long number = nextLong(random, 9_000_000_000L) + 1_000_000_000L;
        return String.valueOf(number);
    }

    public static void createCard(Scanner scanner) {
        // Create a new InsuranceCard object
        InsuranceCard card = new InsuranceCard();

        // Use the IDGenerator method of the new object
        String ID = card.IDGenerator();

        System.out.println("Enter Card Holder's name:");
        String cardHolder = scanner.nextLine();

        System.out.println("Enter Policy Owner's name:");
        String policyOwner = scanner.nextLine();

        System.out.println("Enter Expiry Date (in format yyyy-mm-dd):");
        String expiryDateStr = scanner.nextLine();
        Date expiryDate = null;
        try {
            expiryDate = new SimpleDateFormat("yyyy-MM-dd").parse(expiryDateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter in format yyyy-mm-dd.");
            return;
        }

        // Create a new InsuranceCard object
        InsuranceCard newCard = new InsuranceCard(ID, cardHolder, policyOwner, expiryDate);

        // Add the new card to the list of cards
        cards.add(newCard);

        // Save the card to the file
        LoadSaveData loadSaveData = new LoadSaveData();
        loadSaveData.saveCard(newCard);

        System.out.println("Card created successfully");
    }
    public static void updateCard(Scanner scanner) {
        System.out.println("Enter the ID of the card you want to update:");
        String id = scanner.nextLine();

        // Create an instance of LoadSaveData and load the cards
        LoadSaveData loadSaveData = new LoadSaveData();
        List<InsuranceCard> cards = loadSaveData.loadCard();

        InsuranceCard cardToUpdate = null;
        for (InsuranceCard card : cards) {
            if (card.getID().equals(id)) {
                cardToUpdate = card;
                break;
            }
        }

        if (cardToUpdate == null) {
            System.out.println("Card with ID: " + id + " not found.");
            return;
        }

        System.out.println("Enter new Card Holder's name:");
        String cardHolder = scanner.nextLine();

        System.out.println("Enter new Policy Owner's name:");
        String policyOwner = scanner.nextLine();

        System.out.println("Enter new Expiry Date (in format yyyy-mm-dd):");
        String expiryDateStr = scanner.nextLine();
        Date expiryDate = null;
        try {
            expiryDate = new SimpleDateFormat("yyyy-MM-dd").parse(expiryDateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter in format yyyy-mm-dd.");
            return;
        }

        // Update the card details
        cardToUpdate.setCardHolder(cardHolder);
        cardToUpdate.setPolicyOwner(policyOwner);
        cardToUpdate.setExpiryDate(expiryDate);

        // Update the card in the file
        loadSaveData.updateCard(cards);

        System.out.println("Card updated successfully.");
    }
    public static void readCard(Scanner scanner) {
        System.out.println("Enter the ID of the card you want to read:");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline left-over

        // Create an instance of LoadSaveData and load the cards
        LoadSaveData loadSaveData = new LoadSaveData();
        List<InsuranceCard> cards = loadSaveData.loadCard();

        for (InsuranceCard card : cards) {
            if (card.getID().equals(String.valueOf(id))) {
                // Print the details of the card
                System.out.println("Card ID: " + card.getID());
                System.out.println("Card Holder: " + card.getCardHolder());
                System.out.println("Policy Owner: " + card.getPolicyOwner());
                System.out.println("Expiry Date: " + card.getExpiryDate());
                return;
            }
        }

        System.out.println("Card with ID: " + id + " not found.");
    }
    public static void readAllCards() {
        // Create an instance of LoadSaveData and load the cards
        LoadSaveData loadSaveData = new LoadSaveData();
        List<InsuranceCard> cards = loadSaveData.loadCard();

        // If there are no cards, inform the user
        if (cards.isEmpty()) {
            System.out.println("No cards found.");
            return;
        }

        // Iterate over the list of cards
        for (InsuranceCard card : cards) {
            // Print the details of each card
            System.out.println("Card ID: " + card.getID());
            System.out.println("Card Holder: " + card.getCardHolder());
            System.out.println("Policy Owner: " + card.getPolicyOwner());
            System.out.println("Expiry Date: " + card.getExpiryDate());
            System.out.println("------------------------");
        }
    }

    @Override
    public String toString() {
        return  '{' +
                "ID=" + ID +
                ", CardHolder=" + CardHolder +
                ", PolicyOwner=" + PolicyOwner +
                ", ExpiryDate=" + ExpiryDate +
                '}';
    }
}
