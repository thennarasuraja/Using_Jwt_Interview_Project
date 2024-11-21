package com.example.interviewProcess.Utill;

import com.example.interviewProcess.Model.Employee;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtill {
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(
            "This_is_a_Very_Secure_Key_That_Must_Be_At_Least_64_Characters_Long!".getBytes()
    );
    private  static  final long EXPIRATION_TIME=1000 * 60 * 60;  // for 1 hour

    // token generate method
    public String generateToken(Employee employee) {
        System.out.println("entyemployeecheck"+employee);
        Claims generatetoken= Jwts.claims()
                .setIssuer(employee.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME));

        generatetoken.put("type",employee.getUsertype());
        generatetoken.put("employeeName",employee.getEmployeeName());
        generatetoken.put("id",employee.getId());

        System.out.println("expirytime"+generatetoken.getExpiration());


        return  Jwts.builder()
                .setClaims(generatetoken)
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                .compact();

    }

    //validate Token(optional)
    public Claims validateToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            System.out.println(claims);
            System.out.println("Expiration Time: " + claims.getExpiration());
            return claims;
        } catch (ExpiredJwtException e) {
            System.out.println("Token Is Expired : " + e.getMessage());
            throw  new RuntimeException("Token is Expired");
        } catch (RuntimeException e) {
            System.out.println("Invalid Token :" + e.getMessage());
            throw  new RuntimeException("Invalid Token");
        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());
            throw  new RuntimeException("Unexpected error during token validation");
        }
    }
}
