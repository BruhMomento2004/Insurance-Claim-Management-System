package Classes;

/**
 * @author <Nguyen Thanh Tung - s3979489>
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
        String fileName = "src/Data/bankingInfo.txt";
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName, true)); // true to append to existing file
            writer.write(bankInfo.getID() + ", " + bankInfo.getBankName() + ", " + bankInfo.getAccountNumber());
            writer.newLine(); // to write each object on a new line
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
        String fileName = "src/Data/bankingInfo.txt";
        List<BankingInfo> bankingInfos = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", "); // split the line using the delimiter
                String id = parts[0].trim(); // directly use the first part as ID
                String bankName = parts[1].trim(); // directly use the second part as bankName
                long accountNumber = Long.parseLong(parts[2].trim()); // directly use the third part as accountNumber
                BankingInfo bankInfo = new BankingInfo(id, bankName, accountNumber);
                bankingInfos.add(bankInfo);
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
        String fileName = "src/Data/bankingInfo.txt";
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName, false)); // false to overwrite the file
            for (BankingInfo bankInfo : bankingInfos) {
                writer.write(bankInfo.getID() + ", " +
                        bankInfo.getBankName() + ", " +
                        bankInfo.getAccountNumber());
                writer.newLine(); // to write each object on a new line
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
        String fileName = "src/Data/claims.txt";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName, true)); // true to append to existing file
            writer.write(claim.getID() + ", " +
                    sdf.format(claim.getClaimDate()) + ", " +
                    claim.getInsuredPerson() + ", " +
                    claim.getCardNumber() + ", " +
                    sdf.format(claim.getExamDate()) + ", " +
                    String.join(", ", claim.getDocuments()) + ", " +
                    claim.getClaimAmount() + ", " +
                    claim.getStatus() + ", " +
                    claim.getBankingInfo());
            writer.newLine(); // to write each object on a new line
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
        String fileName = "src/Data/claims.txt";
        List<Claim> claims = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", "); // split the line using the delimiter
                String ID = parts[0].trim();
                Date claimDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[1].trim());
                String insuredPerson = parts[2].trim();
                long cardNumber = Long.parseLong(parts[3].trim());
                Date examDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[4].trim());
                List<String> documents = Arrays.asList(parts[5].trim().split(", "));
                double claimAmount = Double.parseDouble(parts[6].trim());
                Status status = Status.valueOf(parts[7].trim());
                String bankingInfo = parts[8].trim();
                Claim claim = new Claim(ID, claimDate, insuredPerson, cardNumber, examDate, documents, claimAmount, status, bankingInfo);
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
        String fileName = "src/Data/claims.txt";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName, false)); // false to overwrite the file
            for (Claim claim : claims) {
                writer.write(claim.getID() + ", " +
                        sdf.format(claim.getClaimDate()) + ", " +
                        claim.getInsuredPerson() + ", " +
                        claim.getCardNumber() + ", " +
                        sdf.format(claim.getExamDate()) + ", " +
                        String.join(", ", claim.getDocuments()) + ", " +
                        claim.getClaimAmount() + ", " +
                        claim.getStatus() + ", " +
                        claim.getBankingInfo());
                writer.newLine(); // to write each object on a new line
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
