package com.kaysanshi.springsecurityjjwtdemo;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.Base64Codec;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@SpringBootTest
public class SpringSecurityJjwtDemoApplicationTests {
    /**
     * 创建token
     */
    @Test
    public void testCreateToken() {
        // 设置token失效时间：
        long now =System.currentTimeMillis();
        // 过期时间计算
        long exp = now + 60*1000;

        // 创建jwtBuilder对象
        JwtBuilder jwtBuilder = Jwts.builder();
        // 声明标识{"jti":"8888"}
        jwtBuilder.setId("8888");
        // 声明主题{"sub":"Rose"}
        jwtBuilder.setSubject("Rose");
        // 创建日期{"ita":"xxxxx"}
        jwtBuilder.setIssuedAt(new Date());
        // 设置密钥
        jwtBuilder.signWith(SignatureAlgorithm.HS256, "XXXX");
        // 设置过期时间
        jwtBuilder.setExpiration(new Date(exp));
        // 自定义声明
        jwtBuilder.claim("roles","admin");
        jwtBuilder.claim("logo","xxx.jpg");
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
     * 解析token:
     */
    @Test
    public void testParseToken() {
        String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODg4Iiwic3ViIjoiUm9zZSIsImlhdCI6MTYwNDMyMzY1NiwiZXhwIjoxNjA0MzIzNzE2fQ.Qm6yJibqb12tA2txJE_eVa6EjCx1yexK9hRUjTn15-A";
        // 解析token获取负载中声明的对象
        Claims claims = Jwts.parser()
                .setSigningKey("XXXX")
                .parseClaimsJws(token)
                .getBody();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("id:"+claims.getId());
        System.out.println("subject:"+claims.getSubject());
        System.out.println("issuedAt:"+claims.getIssuedAt());
        System.out.println("签发时间:"+simpleDateFormat.format(claims.getIssuedAt()));
        System.out.println("过期时间:"+simpleDateFormat.format(claims.getExpiration()));
        System.out.println("当前时间:"+simpleDateFormat.format(new Date()));
        // 拿到声明
        System.out.println("roles:" +claims.get("roles"));
    }

}
