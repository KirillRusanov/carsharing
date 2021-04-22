package carsharing.configuration.utils;

import carsharing.dao.model.Customer;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Component
public class JwtUtils {

    @Value("${security.jwt.secretKey}")
    private String jwtSecretKey;

    @Value("${security.jwt.expirationToken}")
    private int expirationTokenMs;


    public String generateJwtToken(Authentication authentication) {

        Customer customerPrincipal = (Customer) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((customerPrincipal.getEmail()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expirationTokenMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                .compact();
    }

    public String getEmailFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");
        } catch (MalformedJwtException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        } catch (ExpiredJwtException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "JWT token is expired");
        } catch (UnsupportedJwtException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "JWT token is unsupported");
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "JWT claims string is empty");
        }
    }
}
