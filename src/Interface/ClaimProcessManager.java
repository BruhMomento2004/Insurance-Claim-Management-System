package Interface;

/**
 * @author <Nguyen Thanh Tung - s3979489>
 * Reference: https://github.com/VINAYKUMARKUNDER/Insurance-Management-System.git and
 * https://youtu.be/xNeOHmqNVus?si=4L5anBRVpkQJviVH
 */

import java.util.Scanner;

public interface ClaimProcessManager {
    void addClaim(Scanner scanner);
    void updateClaim(Scanner scanner);
    void deleteClaim(Scanner scanner);
    void getOneClaim(Scanner scanner);
    void getAllClaims();
}
