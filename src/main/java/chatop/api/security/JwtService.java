package chatop.api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import chatop.api.models.entity.User;
import chatop.api.service.UserService;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@AllArgsConstructor
@Service
public class JwtService {

    private final UserService userService;
    private static final String ENCRYPTION_KEY = "837a09cee388e716626da8c3eba4d9c18fe81ba92f84a9bef883114905ab0edc";

    public Map<String, String> generate(String email) {
        // GET EMAIL
        User user = this.userService.loadUserByUsername(email);
        return this.generateJwt(user);
    }

    // TO EXTRACT USER NAME FROM CLAIM TOKEN
    public String extractUserName(String token) {
        return this.getClaim(token, Claims::getSubject);
    }

    // TO CHECK IF TOKEN IS EXPIRED
    public boolean isTokenExpired(String token) {
        // GET DATE FROM TOKEN
        Date expirationDate = this.getClaim(token, Claims::getExpiration);
        // IF EXPIRATION DATE IS BEFORE TODAY DATE THEN ITS NOT EXPIRED
        return expirationDate.before(new Date());
    }

    // METHOD TO GET SOMETHING WE WANT FROM A CLAIM
    private <T> T getClaim(String token, Function<Claims, T> function) {
        // SEARCH INFORMATION IN ALL CLAIMS
        Claims claims = getAllClaims(token);
        return function.apply(claims);
    }

    // METHOD TO GET ALL CLAIMS FROM TOKEN
    private Claims getAllClaims(String token) {
        return Jwts.parser().
                setSigningKey(this.getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // METHOD TO GENERATE JWT/BEARER TOKEN
    private Map<String, String> generateJwt(User user) {

        // SET TIME
        final long currentTime = System.currentTimeMillis();
        final long expirationTime = currentTime + 30 * 60 * 1000;
        // SET CLAIMS STOCKED IN JWT
        final Map<String, Object> claims = Map.of(
                "name", user.getName(),
                Claims.EXPIRATION, new Date(expirationTime),
                Claims.SUBJECT, user.getEmail()
        );

        // JWT BUILDER WITH CLAIMS
        final String jwt = Jwts.builder()
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(expirationTime))
                .setSubject(user.getEmail())
                .setClaims(claims)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
        return Map.of("jwt", jwt);
    }

    // TO SETUP A KEY FOR JWT/BEARER TOKEN
    private Key getKey() {
        final byte[] decoder = Decoders.BASE64.decode(ENCRYPTION_KEY);
        return Keys.hmacShaKeyFor(decoder);
    }

}
