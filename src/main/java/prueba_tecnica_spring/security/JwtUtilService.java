package prueba_tecnica_spring.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * <b>Author:</b> Velfin Velasquez <br>
 * <b>Description:</b> JwtUtilService <br>
 */
@Service
public class JwtUtilService {

    @Value("${JWT_SECRET_KEY}")
    private  String JWT_SECRET_KEY;

    public static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 60 * (long) 8; // 8 Horas

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * @param token String
     * @return Date
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * @param token String
     * @param claimsResolver Function
     * @return <T> T
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(extractAllClaims(token));
    }

    /**
     * @param token String
     * @return Claims
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).getBody();
    }

    /**
     * @param token String
     * @return Boolean
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * @param userDetails UserDetails
     * @return String
     */
    public String generateToken(UserDetails userDetails) {
        System.out.println(userDetails);
        Map<String, Object> claims = new HashMap<>();

        // Agregando informacion adicional como "claim"
        System.out.println(userDetails.getAuthorities());
        claims.put("authorities", userDetails.getAuthorities());
        return createToken(claims, userDetails.getUsername());
    }

    /**
     * @param claims Map<String, Object>
     * @param subject String
     * @return String
     */
    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
                .compact();
    }

    /**
     * @param token String
     * @param userDetails userDetail
     * @return boolean
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}