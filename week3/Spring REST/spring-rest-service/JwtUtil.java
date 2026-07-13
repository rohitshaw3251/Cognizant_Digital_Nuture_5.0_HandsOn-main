package rest;

   import io.jsonwebtoken.Claims;
   import io.jsonwebtoken.Jwts;
   import io.jsonwebtoken.SignatureAlgorithm;
   import io.jsonwebtoken.security.Keys;
   import java.security.Key;
   import java.util.Date;
   import java.util.HashMap;
   import java.util.Map;
   import org.springframework.stereotype.Component;

   @Component
   public class JwtUtil {
       private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
       private static final long EXPIRATION_TIME = 3600000;

       public String generateToken(String username) {
           Map<String, Object> claims = new HashMap<>();
           return Jwts.builder()
                   .setClaims(claims)
                   .setSubject(username)
                   .setIssuedAt(new Date(System.currentTimeMillis()))
                   .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                   .signWith(key)
                   .compact();
       }

       public String extractUsername(String token) {
           return extractAllClaims(token).getSubject();
       }

       public boolean isTokenExpired(String token) {
           return extractAllClaims(token).getExpiration().before(new Date());
       }

       public boolean validateToken(String token, String username) {
           final String extractedUsername = extractUsername(token);
           return (extractedUsername.equals(username) && !isTokenExpired(token));
       }

       private Claims extractAllClaims(String token) {
           return Jwts.parserBuilder()
                   .setSigningKey(key)
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
       }
   }
