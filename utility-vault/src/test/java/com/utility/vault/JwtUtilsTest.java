package com.utility.vault;

import static org.junit.jupiter.api.Assertions.*;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.JsonObject;
import com.utility.vault.security.jwt.JwtUtils;
import com.utility.vault.security.jwt.JwtVerificationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class JwtUtilsTest {
    private static String secret;
    private static String validToken;
    private static String invalidToken;

    @BeforeAll
    public static void setup() {
        secret = "mysecret123";

        Algorithm algorithm = Algorithm.HMAC256(secret);
        validToken = JWT.create()
                .withClaim("username", "johndoe")
                .withClaim("fullName", "John Doe")
                .sign(algorithm);

        Algorithm badAlgorithm = Algorithm.HMAC256("wrongsecret");
        invalidToken = JWT.create()
                .withClaim("username", "johndoe")
                .withClaim("fullName", "John Doe")
                .sign(badAlgorithm);
    }

    @Test
    public void testVerifyAndDecode_validToken() {
        DecodedJWT decoded = JwtUtils.verifyAndDecode(validToken, secret);
        assertNotNull(decoded);
        assertEquals("johndoe", decoded.getClaim("username").asString());
        assertEquals("John Doe", decoded.getClaim("fullName").asString());
    }

    @Test
    public void testVerifyAndDecode_invalidToken_throws() {
        JwtVerificationException exception = assertThrows(JwtVerificationException.class, () -> {
            JwtUtils.verifyAndDecode(invalidToken, secret);
        });

        assertTrue(exception.getMessage().contains("Token verification failed"));
    }

    @Test
    public void testDecodeToken_payloadDecodedCorrectly() {
        JsonObject payload = JwtUtils.decodePayloadWithoutVerification(validToken);
        Optional<String> username = JwtUtils.getUsername(payload);
        Optional<String> fullName = JwtUtils.getFullName(payload);

        assertTrue(username.isPresent());
        assertEquals("johndoe", username.get());

        assertTrue(fullName.isPresent());
        assertEquals("John Doe", fullName.get());
    }

    @Test
    public void testDecodeToken_invalidFormat_throws() {
        String badToken = "abc.def"; // missing third part

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            JwtUtils.decodePayloadWithoutVerification(badToken);
        });
        assertTrue(ex.getMessage().contains("Invalid JWT format"));
    }

    @Test
    public void testGetUsername_andGetFullName_emptyIfMissing() {
        JsonObject emptyPayload = new JsonObject();

        Optional<String> username = JwtUtils.getUsername(emptyPayload);
        Optional<String> fullName = JwtUtils.getFullName(emptyPayload);

        assertFalse(username.isPresent());
        assertFalse(fullName.isPresent());
    }

    @Test
    public void testIsNotEmpty() {
        assertTrue(JwtUtils.isNotEmpty("test"));
        assertTrue(JwtUtils.isNotEmpty("  test  "));
        assertFalse(JwtUtils.isNotEmpty(null));
        assertFalse(JwtUtils.isNotEmpty(""));
        assertFalse(JwtUtils.isNotEmpty("   "));
    }
}
