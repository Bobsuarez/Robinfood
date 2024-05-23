package com.robinfood.app.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@TestPropertySource(properties = {
        "jwt.token.secret=secretForTesting",
        "jwt-token-aud=internal"
})
public class BaseTestController {

    protected String getJWT(String modules, String permissions) {

        String secretKey = "secretForTesting";

        List<GrantedAuthority> listModules = AuthorityUtils
                .commaSeparatedStringToAuthorityList(modules);

        List<GrantedAuthority> listPermissions = AuthorityUtils
                .commaSeparatedStringToAuthorityList(permissions);

        Map<String, String> user = new HashMap<>();
        user.put("user_id", "123");

        String token = Jwts
                .builder()
                .setId(LocalDateTime.now().toString())
                .setSubject("123")
                .setAudience("internal")
                .claim("mod",
                        listModules.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .claim("per",
                        listPermissions.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .claim("user", user)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60000000))
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

}
