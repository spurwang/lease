package com.atguigu.lease.common.utils;

import com.atguigu.lease.common.exception.LeaseException;
import com.atguigu.lease.common.result.ResultCodeEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtUtil {
    private static SecretKey secretKey = Keys.hmacShaKeyFor("SJeThVZkJDE6uDYnA2vHurSmsu6GY3Xm".getBytes());
    public static String createToke(Long userId,String username){

        String jwt = Jwts.builder().
                setExpiration(new Date(System.currentTimeMillis() + 3600000)).
                setSubject("LOGIN_USER").
                claim("userId", userId).
                claim("username", username).
                signWith(secretKey, SignatureAlgorithm.HS256).
                compact();
        return jwt;
    }
    public static Claims parseToken(String token){
        if(token==null){
            throw new LeaseException(ResultCodeEnum.ADMIN_LOGIN_AUTH);
        }
        try{
            JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();
            return jwtParser.parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e){
            throw new LeaseException(ResultCodeEnum.TOKEN_EXPIRED);
        }catch (JwtException e){
            throw new LeaseException(ResultCodeEnum.TOKEN_INVALID);
        }


    }
    public static void main(String[] args){
        System.out.println(createToke(8L, "13562245770"));
    }
}
