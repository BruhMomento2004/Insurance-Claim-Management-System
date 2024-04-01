package Classes;

import java.util.ArrayList;
import java.util.List;

public class ClaimManager {
    private static List<Claim> claims = new ArrayList<>();

    public static void addClaim(Claim claim) {
        claims.add(claim);
    }

    // Other static methods to manage claims...
}
