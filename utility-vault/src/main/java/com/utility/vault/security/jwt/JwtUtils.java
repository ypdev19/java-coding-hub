package com.utility.vault.security.jwt;

import java.util.Base64;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import java.util.Optional;

/**
 * Class for functions of common use related with JWT.
 */
public class JwtUtils {

    private final static String USERNAME = "username";
    private final static String FULLNAME = "fullName";
    private static final String ISSUER = "auth0"; // Optional: change to match your issuer

    /**
     * Verifies and decodes a JWT token using HMAC256 algorithm.
     *
     * @param token  the JWT token to verify
     * @param secret the shared secret used to sign the token
     * @return Decoded JWT if valid
     * @throws RuntimeException if verification fails
     */
    public static DecodedJWT verifyAndDecode(String token, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    // no issuer check here
                    .build();

            return verifier.verify(token); // Throws if invalid
        } catch (JWTVerificationException e) {
            throw new JwtVerificationException("Token verification failed: " + e.getMessage(), e);
        }
    }

    /**
     * Decodes the token payload without verifying the signature.
     * (To use only for non-sensitive or debug scenarios.)
     *
     * @param token the token to decode
     * @return JsonObject representing payload
     */
    public static JsonObject decodePayloadWithoutVerification(String token) {
        try {
            String[] parts = token.split("\\.");

            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid JWT format.");
            }

            String payload = parts[1];
            String decodedPayload = new String(Base64.getUrlDecoder().decode(payload));

            return JsonParser.parseString(decodedPayload).getAsJsonObject();
        } catch (Exception e) {
            throw new JwtVerificationException("Token verification failed: " + e.getMessage(), e);
        }
    }

    /**
     * Extracts the username parameter from the token payload.
     * If the parameter does not exist, will return a null
     *
     * @param decodedToken
     * @return The username value
     */
    public static Optional<String> getUsername(JsonObject decodedToken) {
        return decodedToken.has(USERNAME)
                ? Optional.ofNullable(decodedToken.get(USERNAME).getAsString())
                : Optional.empty();
    }

    /**
     * Extracts the fullName parameter from the token payload.
     * If the parameter does not exist, will return a null.
     *
     * @param decodedToken
     * @return the fullName value
     */
    public static Optional<String> getFullName(JsonObject decodedToken) {
        return decodedToken.has(FULLNAME)
                ? Optional.ofNullable(decodedToken.get(FULLNAME).getAsString())
                : Optional.empty();
    }

    /**
     * This checks if a string is not null and not empty.
     *
     * @param value the value to check
     * @return true if is not null and not empty, false otherwise.
     */
    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
