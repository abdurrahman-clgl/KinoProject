package UnsereWelt.security.filter;

import UnsereWelt.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    // 🔐 Geheimer Schlüssel wird aus application.properties geladen
    @Value("${jwt.secret}")
    private String secretKey;

    // 🔑 Schlüsselobjekt erzeugen (für Signieren und Verifizieren)
    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // 🎟️ Token für den angemeldeten Benutzer erzeugen
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail()) // Subject = Benutzerkennung
                .claim("role", user.getRole().name()) // Rolle im Token speichern
                .setIssuedAt(new Date()) // Ausstellungsdatum
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24h gültig
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // Signatur
                .compact(); // Token erzeugen
    }

    // 📬 E-Mail (Subject) aus dem Token extrahieren
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // ✅ Gültigkeit des Tokens prüfen
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return email.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    // ⏳ Ablauf prüfen
    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
}
