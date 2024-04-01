package Classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class InsuranceCard {
    private int ID;
    private String CardHolder;
    private String PolicyOwner;
    private Date ExpiryDate;
    public InsuranceCard() {
    }
    public InsuranceCard(int ID, String cardHolder, String policyOwner, Date expiryDate) {
        this.ID = ID;
        this.CardHolder = cardHolder;
        this.PolicyOwner = policyOwner;
        this.ExpiryDate = expiryDate;
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
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

    public static void readAllCards() {
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

    public static void createCard(Scanner scanner) {
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

        // Generate a unique ID for the card
        int id = cards.size() + 1;

        // Create a new InsuranceCard object
        InsuranceCard newCard = new InsuranceCard(id, cardHolder, policyOwner, expiryDate);

        // Add the new card to the list of cards
        cards.add(newCard);

        System.out.println("Card created successfully with ID: " + id);
    }
    public static void updateCard(Scanner scanner) {
        System.out.println("Enter the ID of the card you want to update:");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline left-over

        for (InsuranceCard card : cards) {
            if (card.getID() == id) {
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
                card.setCardHolder(cardHolder);
                card.setPolicyOwner(policyOwner);
                card.setExpiryDate(expiryDate);

                System.out.println("Card updated successfully.");
                return;
            }
        }
        System.out.println("Card with ID: " + id + " not found.");
    }
    public static void readCard(Scanner scanner) {
        System.out.println("Enter the ID of the card you want to read:");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline left-over

        for (InsuranceCard card : cards) {
            if (card.getID() == id) {
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
}
