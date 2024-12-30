package org.green.backend.utils;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {

    private SecretKey secretKey;

    public JWTUtil(@Value("${spring.jwt.secret}") String secret) {
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm());

//        System.out.println("Secret Key: " + secretKey.toString());
//        System.out.println("Algorithm: " + secretKey.getAlgorithm());
    }

    public String getId(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseClaimsJws(token)
                .getPayload().get("id", String.class);
    }

    public String getUserGbnCd(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseClaimsJws(token)
                .getPayload().get("userGbnCd", String.class);
    }

    public String getName(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseClaimsJws(token)
                .getPayload().get("name", String.class);
    }

    public String getUseYn(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseClaimsJws(token)
                .getPayload().get("useYn", String.class);
    }

    public Boolean isExpired(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token)
                .getPayload().getExpiration().before(new Date());
    }

    public String createJwt(String id,
                            String name,
                            String userGbnCd,
                            String useYn,
                            Long expiredMs) {
        return Jwts.builder()
                .claim("id", id)
                .claim("name", name)
                .claim("userGbnCd", userGbnCd)
                .claim("useYn", useYn)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }
}
