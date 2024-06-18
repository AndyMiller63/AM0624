package com.windmill.rentalservice.config.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.windmill.rentalservice.model.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenProvider {

    private final String jwtSecret;
    private final long jwtExpirationMs;

    //@Autowired
    public TokenProvider() {
        this.jwtSecret = "ThisSpaceForRent"; //dotenv.get("JWT_SECRET");
        this.jwtExpirationMs = 3600000000L; //Long.parseLong(dotenv.get("JWT_EXPIRATION_MS"));
    }

    /**
     * Generates an access token for a given user.
     *
     * @param user the User
     * @return the generated access token
     */
    public String generateAccessToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            return JWT.create()
                    .withSubject(user.getUsername())
                    .withClaim("username", user.getUsername())
                    .withExpiresAt(genAccessExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    /**
     * Validates the given token and returns the username.
     *
     * @param token the token to validate
     * @return the username
     */
    public String validateToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(jwtSecret))
                    .build()
                    .verify(token)
                    .getClaim("username")
                    .asString();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Error while validating token", exception);
        }
    }

    /**
     * Generates the expiration date for the access token.
     *
     * @return the expiration date
     */
    private Date genAccessExpirationDate() {
        return new Date(System.currentTimeMillis() + jwtExpirationMs);
    }
}
