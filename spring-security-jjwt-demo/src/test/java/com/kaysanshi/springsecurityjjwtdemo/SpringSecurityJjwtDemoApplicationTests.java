package com.kaysanshi.springsecurityjjwtdemo;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.Base64Codec;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Base64;
import java.util.Date;

@SpringBootTest
public class SpringSecurityJjwtDemoApplicationTests {
    /**
     * 创建token
     */
    @Test
    public void testCreateToken() {
        // 创建jwtBuilder对象
        JwtBuilder jwtBuilder = Jwts.builder();
        // 声明标识{"jti":"8888"}
        jwtBuilder.setId("8888");
        // 声明主题{"sub":"Rose"}
        jwtBuilder.setSubject("Rose");
        // 创建日期{"ita":"xxxxx"}
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS256, "XXXX");
        // 获取jwt token
        String token = jwtBuilder.compact();
        System.out.println(token);

        System.out.println("==================");
        String[] split = token.split("\\.");
        System.out.println(Base64Codec.BASE64.decodeToString(split[0]));
        System.out.println(Base64Codec.BASE64.decodeToString(split[1]));

        // 盐是无法解密的。
        System.out.println(Base64Codec.BASE64.decodeToString(split[2]));

    }

    /**
     * 解析token： 有问题
     */
    @Test
    public void testParseToken() {
        String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODg4Iiwic3ViIjoiUm9zZSIsImlhdCI6MTYwNDIzNjE5M30.C3aK1iVCZCW9LgtA8Y_QVX4H9ypQ3yGxoUmeR9rc4_w\n";
        // 解析token获取负载中声明的对象
        Claims claims = Jwts.parser()
                .setSigningKey("XXXX")
                .parseClaimsJws(token)
                .getBody();
        System.out.println("id:"+claims.getId());
        System.out.println("subject:"+claims.getSubject());
        System.out.println("issuedAt:"+claims.getIssuedAt());

    }

}
