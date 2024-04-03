package Classes;

/**
 * @author <Nguyen Thanh Tung - s3979489>
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoadSaveData {
    public void saveBankingInfo(BankingInfo bankInfo, String fileName) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName, true)); // true to append to existing file
            writer.write(bankInfo.toString());
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
    public List<BankingInfo> loadBankingInfo(String fileName) {
        List<BankingInfo> bankingInfos = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", "); // split the line using the delimiter
                String id = parts[0];
                String bankName = parts[1];
                double accountNumber = Double.parseDouble(parts[2]);
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
}
