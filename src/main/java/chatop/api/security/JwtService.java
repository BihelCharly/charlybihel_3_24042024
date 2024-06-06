package chatop.api.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import chatop.api.models.entities.User;
import chatop.api.service.UsersService;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@AllArgsConstructor
@Service
public class JwtService {

    private final UsersService usersService;
    private static final String ENCRYPTION_KEY = "837a09cee388e716626da8c3eba4d9c18fe81ba92f84a9bef883114905ab0edc";

    public Map<String, String> generate(String email) {
        User user = this.usersService.loadUserByUsername(email);
        return this.generateJwt(user);
    }

    private Map<String, String> generateJwt(User user) {

        final Map<String, String> claims = Map.of(
                "name", user.getName(),
                "email", user.getEmail()
        );
        final long currentTime = System.currentTimeMillis();
        final long expirationTime = currentTime + 30 * 60 * 1000;

        final String bearer = Jwts.builder()
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(expirationTime))
                .setSubject(user.getEmail())
                .setClaims(claims)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
        return Map.of("bearer", bearer);
    }

    private Key getKey() {
        final byte[] decoder = Decoders.BASE64.decode(ENCRYPTION_KEY);
        return Keys.hmacShaKeyFor(decoder);
    }

}
