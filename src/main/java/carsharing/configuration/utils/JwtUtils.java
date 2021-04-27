package carsharing.configuration.utils;

import carsharing.dao.model.Customer;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

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

    public boolean validateJwtToken(String authToken) throws JwtException {
        Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(authToken);
        return true;
    }
}
