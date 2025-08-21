package org.wujunyang;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    @Test
    public void test(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", "1");
        claims.put("username", "wujunyang");
        claims.put("name", "王俊阳");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "wujunyang")
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .compact();
        System.out.println(jwt);
    }
    @Test
    public void test2(){
            Claims claims = Jwts.parser()
                    .setSigningKey("wujunyang")
                    .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi546L5L-K6ZizIiwiaWQiOiIxIiwidXNlcm5hbWUiOiJ3dWp1bnlhbmciLCJleHAiOjE3NTQ4MTIwNzJ9.IKHd8pFeUoEZdxJd0oLfb1YRl7toAh76nHd1U6xO9NQ")
                    .getBody();
        System.out.println(claims);

    }

}
