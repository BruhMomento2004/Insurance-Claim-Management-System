package Classes;

/**
 * @author <Nguyen Thanh Tung - s3979489>
 * Reference: https://github.com/VINAYKUMARKUNDER/Insurance-Management-System.git and
 * https://youtu.be/xNeOHmqNVus?si=4L5anBRVpkQJviVH
 */

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import Enum.Status;

public class LoadSaveData {
    public void saveBankingInfo(BankingInfo bankInfo) {
        String fileName = "src/Data/BankingInfo.txt";
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName, true)); // true to append to existing file
            writer.write(bankInfo.getID() + ", " + bankInfo.getBankName() + ", " + bankInfo.getAccountNumber());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public List<BankingInfo> loadBankingInfo() {
        String fileName = "src/Data/BankingInfo.txt";
        List<BankingInfo> bankingInfos = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length < 3) {
                    System.out.println("Skipping line due to insufficient data: " + line);
                    continue;
                }
                String id = parts[0].trim();
                String bankName = parts[1].trim();
                double accountNumberDouble = Double.parseDouble(parts[2].trim());
                long accountNumber = (long) accountNumberDouble;
                BankingInfo bankingInfo = new BankingInfo(id, bankName, accountNumber);
                bankingInfos.add(bankingInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bankingInfos;
    }
    public void updateBankingInfo(List<BankingInfo> bankingInfos) {
        String fileName = "src/Data/BankingInfo.txt";
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName, false)); // false to overwrite the file
            for (BankingInfo bankInfo : bankingInfos) {
                writer.write(bankInfo.getID() + ", " +
                        bankInfo.getBankName() + ", " +
                        bankInfo.getAccountNumber());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void saveClaim(Claim claim) {
        String fileName = "src/Data/Claims.txt";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName, true)); // true to append to existing file
            writer.write(claim.getID() + ", " +
                    sdf.format(claim.getExamDate()) + ", " +
                    claim.getInsuredPerson() + ", " +
                    claim.getCardNumber() + ", " +
                    sdf.format(claim.getClaimDate()) + ", " +
                    String.join(", ", claim.getDocuments()) + ", " +
                    claim.getClaimAmount() + ", " +
                    claim.getStatus() + ", " +
                    claim.getBankingInfo());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public List<Claim> loadClaim() {
        String fileName = "src/Data/Claims.txt";
        List<Claim> claims = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", "); // split the line using the delimiter
                String ID = parts[0].trim();
                Date examDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[1].trim());
                String insuredPerson = parts[2].trim();
                long cardNumber = Long.parseLong(parts[3].trim());
                Date claimDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[4].trim());
                List<String> documents = Arrays.asList(parts[5].trim().split(", "));
                double claimAmount = Double.parseDouble(parts[6].trim());
                Status status = Status.valueOf(parts[7].trim());
                String bankingInfo = parts[8].trim();
                Claim claim = new Claim(ID, examDate, insuredPerson, cardNumber, claimDate, documents, claimAmount, status, bankingInfo);
                claims.add(claim);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return claims;
    }
    public void updateClaims(List<Claim> claims) {
        String fileName = "src/Data/Claims.txt";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName, false)); // false to overwrite the file
            for (Claim claim : claims) {
                writer.write(claim.getID() + ", " +
                        sdf.format(claim.getExamDate()) + ", " +
                        claim.getInsuredPerson() + ", " +
                        claim.getCardNumber() + ", " +
                        sdf.format(claim.getClaimDate()) + ", " +
                        String.join(", ", claim.getDocuments()) + ", " +
                        claim.getClaimAmount() + ", " +
                        claim.getStatus() + ", " +
                        claim.getBankingInfo());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void saveCard(InsuranceCard card) {
        String fileName = "src/Data/InsuranceCard.txt";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName, true)); // true to append to existing file
            writer.write(card.getID() + ", c-" + card.getCardHolder() + ", c-" + card.getPolicyOwner() + ", " + sdf.format(card.getExpiryDate()));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public List<InsuranceCard> loadCard() {
        String fileName = "src/Data/InsuranceCard.txt";
        List<InsuranceCard> cards = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", "); // split the line using the delimiter
                if (parts.length < 4) {
                    System.out.println("Skipping line due to insufficient data: " + line);
                    continue;
                }
                String id = parts[0].trim(); // directly use the first part as ID
                String cardHolder = parts[1].substring(2).trim(); // remove the 'c-' prefix and use the second part as cardHolder
                String policyOwner = parts[2].substring(2).trim(); // remove the 'c-' prefix and use the third part as policyOwner
                Date expiryDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[3].trim()); // directly use the fourth part as expiryDate
                InsuranceCard card = new InsuranceCard(id, cardHolder, policyOwner, expiryDate);
                cards.add(card);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cards;
    }
    public void updateCard(List<InsuranceCard> cards) {
        String fileName = "src/Data/InsuranceCard.txt";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName, false)); // false to overwrite the file
            for (InsuranceCard card : cards) {
                writer.write(card.getID() + ", c-" +
                        card.getCardHolder() + ", c-" +
                        card.getPolicyOwner() + ", " +
                        sdf.format(card.getExpiryDate()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void saveDependent(Dependent dependent) {
        String fileName = "src/Data/Dependent.txt";
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName, true)); // true to append to existing file
            writer.write(dependent.getCustomerID() + ", " +
                    dependent.getCustomerFullName() + ", " +
                    dependent.getCustomerInsuranceCard() + ", " +
                    String.join("_", dependent.getCustomerClaims()) + ", " +
                    dependent.getPolicyHolderId());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public List<Dependent> loadDependent() {
        String fileName = "src/Data/Dependent.txt";
        List<Dependent> dependents = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", "); // split the line using the delimiter
                String customerID = parts[0].trim(); // directly use the first part as customerID
                String customerFullName = parts[1].trim(); // directly use the second part as customerFullName
                long customerInsuranceCard = Long.parseLong(parts[2].trim()); // directly use the third part as customerInsuranceCard
                List<String> customerClaims = Arrays.asList(parts[3].trim().split("_")); // directly use the fourth part as customerClaims
                String policyHolderId = parts[4].trim(); // directly use the fifth part as policyHolderId
                Dependent dependent = new Dependent(customerID, customerFullName, customerInsuranceCard, customerClaims, policyHolderId);
                dependents.add(dependent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dependents;
    }
    public void updateDependent(List<Dependent> dependents) {
        String fileName = "src/Data/Dependent.txt";
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName, false)); // false to overwrite the file
            for (Dependent dependent : dependents) {
                writer.write(dependent.getCustomerID() + ", " +
                        dependent.getCustomerFullName() + ", " +
                        dependent.getCustomerInsuranceCard() + ", " +
                        String.join("_", dependent.getCustomerClaims()) + ", " +
                        dependent.getPolicyHolderId());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void savePolicyHolder(PolicyHolder policyHolder) {
        String fileName = "src/Data/PolicyHolder.txt";
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName, true)); // true to append to existing file
            writer.write(policyHolder.getCustomerID() + ", " +
                    policyHolder.getCustomerFullName() + ", " +
                    policyHolder.getCustomerInsuranceCard() + ", " +
                    String.join("_", policyHolder.getCustomerClaims()) + ", " +
                    String.join("_", policyHolder.getDependents()));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public List<PolicyHolder> loadPolicyHolder() {
        String fileName = "src/Data/PolicyHolder.txt";
        List<PolicyHolder> policyHolders = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", "); // split the line using the delimiter
                String customerID = parts[0].trim(); // directly use the first part as customerID
                String customerFullName = parts[1].trim(); // directly use the second part as customerFullName
                long customerInsuranceCard = Long.parseLong(parts[2].trim()); // directly use the third part as customerInsuranceCard
                List<String> customerClaims = Arrays.asList(parts[3].trim().split("_")); // directly use the fourth part as customerClaims
                List<String> dependents = Arrays.asList(parts[4].trim().split("_")); // directly use the fifth part as dependents
                PolicyHolder policyHolder = new PolicyHolder(customerID, customerFullName, customerInsuranceCard, customerClaims, dependents);
                policyHolders.add(policyHolder);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return policyHolders;
    }
    public void updatePolicyHolder(List<PolicyHolder> policyHolders) {
        String fileName = "src/Data/PolicyHolder.txt";
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName, false)); // false to overwrite the file
            for (PolicyHolder policyHolder : policyHolders) {
                writer.write(policyHolder.getCustomerID() + ", " +
                        policyHolder.getCustomerFullName() + ", " +
                        policyHolder.getCustomerInsuranceCard() + ", " +
                        String.join("_", policyHolder.getCustomerClaims()) + ", " +
                        String.join("_", policyHolder.getDependents()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
