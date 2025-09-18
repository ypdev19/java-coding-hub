package com.utility.vault.security.jwt;

/**
 * Exception thrown when JWT verification fails.
 * <p>
 * This exception is used to wrap any underlying {@link com.auth0.jwt.exceptions.JWTVerificationException}
 * thrown by the JWT library, and allows consumers of the {@code JwtUtils} class to catch a domain-specific
 * error rather than a generic runtime exception.
 * </p>
 *
 * <p>Typical causes for this exception include:</p>
 * <ul>
 *   <li>Invalid token signature</li>
 *   <li>Malformed token</li>
 *   <li>Incorrect secret key</li>
 *   <li>Unsupported algorithm</li>
 *   <li>Expired or otherwise invalid claims</li>
 * </ul>
 *
 * @author ypdev19
 */
public class JwtVerificationException extends RuntimeException {

    /**
     * Constructs a new {@code JwtVerificationException} with the specified detail message.
     *
     * @param message the detail message
     */
    public JwtVerificationException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code JwtVerificationException} with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause (can be retrieved later with {@link #getCause()})
     */
    public JwtVerificationException(String message, Throwable cause) {
        super(message, cause);
    }
}
