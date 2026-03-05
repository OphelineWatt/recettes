package foreach.cda.recettes.config;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
    private static final String SECRET = "e74866bbb057955991aab1de5848504191136aba1b35588e46acc85870e126c39a6b347c766940f4699eda1c64749267f549705a95a8c5cbf8d002fae8ff7647";
    private static final long EXPIRATION = 1000 * 60 * 60; // 1 heure

    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes()); 

    /**
     * Génère un JWT minimaliste à partir d'un nom d'utilisateur.  
     * Un surchargé prend un objet User pour pousser l'ID et le rôle
     * en claims afin de faciliter la gestion des permissions.
     */
    public static String generateToken(String username){
        try {
            return  Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    public static String generateToken(foreach.cda.recettes.entities.User user){
        try {
            return Jwts.builder()
                    .setSubject(user.getMail())
                    .claim("id", user.getIdUser())
                    .claim("role", user.getRole() != null && user.getRole() ? "ADMIN" : "USER")
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    public static boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String extractUsername(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public static Integer extractUserId(String token){
        Object val = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("id");
        if (val instanceof Integer) return (Integer) val;
        if (val instanceof Long) return ((Long) val).intValue();
        return null;
    }

    public static String extractRole(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("role", String.class);
    }
}