package com.utility.vault;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.JsonObject;
import com.utility.vault.date.DateUtils;
import com.utility.vault.security.jwt.JwtUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.format.DateTimeFormatter;

/**
 * Main class - project execution
 *
 * @author ypdev19
 */
public class AppRunner {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss z";
    private static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";

    public static void main(String[] args) {
        useDateUtils();
        useJWTUtils();
    }

    private static void useDateUtils() {
        // Get current date
        System.out.println("Current date: " + DateUtils.getCurrentDate(DateTimeFormatter.ofPattern(SHORT_DATE_FORMAT)));

        // Get future date
        String futureDate = DateUtils.getFutureDateTime(5, 9, 30, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        System.out.println("Future date: " + futureDate);
    }

    private static void useJWTUtils() {
        String secret = "mysecret123";
        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create()
                .withClaim("username", "johndoe")
                .withClaim("fullName", "John Doe")
                .sign(algorithm);

        System.out.println("Generated Token: " + token);

        try {
            // Step 1: Verify and decode the token
            DecodedJWT verifiedJwt = JwtUtils.verifyAndDecode(token, secret);
            // Step 2: Get claims directly
            String username = verifiedJwt.getClaim("username").asString();
            String fullName = verifiedJwt.getClaim("fullName").asString();

            System.out.println("Token is valid.");
            System.out.println("Username: " + username);
            System.out.println("Full Name: " + fullName);

        } catch (RuntimeException ex) {
            System.err.println("Invalid JWT: " + ex.getMessage());
        }
    }

}
