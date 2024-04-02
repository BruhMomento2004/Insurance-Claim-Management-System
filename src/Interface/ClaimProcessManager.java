package Interface;

/**
 * @author <Nguyen Thanh Tung - s3979489>
 */

import java.util.Scanner;

public interface ClaimProcessManager {
    void addClaim(Scanner scanner);
    void updateClaim(Scanner scanner);
    void deleteClaim(Scanner scanner);
    void getOneClaim(Scanner scanner);
    void getAllClaims();
}
