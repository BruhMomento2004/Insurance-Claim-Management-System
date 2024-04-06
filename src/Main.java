//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import Classes.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to Tung's Insurance Claim Management Systems!");
            System.out.println("Please select an option:");
            System.out.println("1. Banking Operations");
            System.out.println("2. Claim Operations");
            System.out.println("3. Insurance Card Operations");
            System.out.println("4. Customer Operations");
            System.out.println("5. Exit");

            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline left-over

            switch (option) {
                case 1:
                    bankingOperations(scanner);
                    break;
                case 2:
                    claimOperations(scanner);
                    break;
                case 3:
                    insuranceCardOperations(scanner);
                    break;
                case 4:
                    CustomerUI.main(new String[]{});
                    break;
                case 5:
                    System.out.println("Exiting the system...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void bankingOperations(Scanner scanner) {
        while (true) {
            System.out.println("Banking Operations");
            System.out.println("Please select an option:");
            System.out.println("1. Create Bank");
            System.out.println("2. Update Bank");
            System.out.println("3. Read Bank");
            System.out.println("4. Read All Banks");
            System.out.println("5. Back to Main Menu");

            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline left-over

            switch (option) {
                case 1:
                    BankingInfo.createBank(scanner);
                    break;
                case 2:
                    BankingInfo.updateBank(scanner);
                    break;
                case 3:
                    BankingInfo.readBank(scanner);
                    break;
                case 4:
                    BankingInfo.readAllBanks();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void claimOperations(Scanner scanner) {
        Claim claim = new Claim();

        while (true) {
            System.out.println("Claim Operations");
            System.out.println("Please select an option:");
            System.out.println("1. Add Claim");
            System.out.println("2. Update Claim");
            System.out.println("3. Delete Claim");
            System.out.println("4. Retrieve One Claim");
            System.out.println("5. Retrieve All Claims");
            System.out.println("6. Back to Main Menu");

            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline left-over

            switch (option) {
                case 1:
                    claim.addClaim(scanner);
                    break;
                case 2:
                    claim.updateClaim(scanner);
                    break;
                case 3:
                    claim.deleteClaim(scanner);
                    break;
                case 4:
                    claim.getOneClaim(scanner);
                    break;
                case 5:
                    claim.getAllClaims();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    public static void insuranceCardOperations(Scanner scanner) {
        while (true) {
            System.out.println("Insurance Card Operations");
            System.out.println("Please select an option:");
            System.out.println("1. Create Card");
            System.out.println("2. Update Card");
            System.out.println("3. Read Card");
            System.out.println("4. Read All Cards");
            System.out.println("5. Back to Main Menu");

            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline left-over

            switch (option) {
                case 1:
                    InsuranceCard.createCard(scanner);
                    break;
                case 2:
                    InsuranceCard.updateCard(scanner);
                    break;
                case 3:
                    InsuranceCard.readCard(scanner);
                    break;
                case 4:
                    InsuranceCard.readAllCards();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    public static class CustomerUI {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Welcome to the Customer Management System!");
                System.out.println("Please select an option:");
                System.out.println("1. Policy Holder Operations");
                System.out.println("2. Dependent Operations");
                System.out.println("3. Back to Main Menu");
                System.out.println("4. Exit the System");

                int option = scanner.nextInt();
                scanner.nextLine(); // consume newline left-over

                switch (option) {
                    case 1:
                        policyHolderOperations(scanner);
                        break;
                    case 2:
                        dependentOperations(scanner);
                        break;
                    case 3:
                        return; // Back to Main Menu
                    case 4:
                        System.out.println("Exiting the system...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }

        public static void policyHolderOperations(Scanner scanner) {
            PolicyHolder policyHolder = new PolicyHolder();

            while (true) {
                System.out.println("Policy Holder Operations");
                System.out.println("Please select an option:");
                System.out.println("1. Create Policy Holder");
                System.out.println("2. Update Policy Holder");
                System.out.println("3. Read Policy Holder");
                System.out.println("4. Read All Policy Holders");
                System.out.println("5. Back to Main Menu");

                int option = scanner.nextInt();
                scanner.nextLine(); // consume newline left-over

                switch (option) {
                    case 1:
                        policyHolder.createCustomer(scanner);
                        break;
                    case 2:
                        policyHolder.updateCustomer(scanner);
                        break;
                    case 3:
                        policyHolder.readCustomer(scanner);
                        break;
                    case 4:
                        policyHolder.readAllCustomers();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }

        public static void dependentOperations(Scanner scanner) {
            Dependent dependent = new Dependent();

            while (true) {
                System.out.println("Dependent Operations");
                System.out.println("Please select an option:");
                System.out.println("1. Create Dependent");
                System.out.println("2. Update Dependent");
                System.out.println("3. Read Dependent");
                System.out.println("4. Read All Dependents");
                System.out.println("5. Back to Main Menu");

                int option = scanner.nextInt();
                scanner.nextLine(); // consume newline left-over

                switch (option) {
                    case 1:
                        dependent.createCustomer(scanner);
                        break;
                    case 2:
                        dependent.updateCustomer(scanner);
                        break;
                    case 3:
                        dependent.readCustomer(scanner);
                        break;
                    case 4:
                        dependent.readAllCustomers();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
}