package com.delivery.deliveryapi.security.jwt;

import com.delivery.deliveryapi.model.User;
import com.delivery.deliveryapi.model.UserRoleEnum;
import com.delivery.deliveryapi.security.PrincipalDetails;
import com.delivery.deliveryapi.security.PrincipalDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class JwtUtil {
    @Value("${custom.jwt.secretKey")
    private String secretKey;
    private static final long TOKEN_VALID_TIME = 1000L * 10;

    private final PrincipalDetailsService principalDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
    public String generateJwtToken(User user) {
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("username", user.getUsername());
        payloads.put("user_id", user.getId());
        payloads.put("role", user.getRole().getAuthority());
        Date now = new Date();
        return Jwts.builder()
                .setClaims(payloads) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + TOKEN_VALID_TIME)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        // UserDetails userDetails = principalDetailsService.loadUserByUsername((String) this.getData(token).get("username"));
        System.out.println("getAuthentication");
        System.out.println( this.getData(token));
        PrincipalDetails userDetails = new PrincipalDetails(
                (String) this.getData(token).get("username"),
                this.getData(token).get("user_id").toString(),
                (String) this.getData(token).get("role"));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
    public Map<String, Object> getData(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
}
