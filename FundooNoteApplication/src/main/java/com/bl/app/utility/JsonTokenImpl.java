package com.bl.app.utility;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
public class JsonTokenImpl implements JsonToken{
	@Override
	public int tokenVerification(String token) {
		// This line will throw an exception if it is not a signed JWS (as expected)
		if (StringUtils.isEmpty(token)) {
		}
		Claims claims = Jwts.parser().setSigningKey("base64SecretBytes").parseClaimsJws(token).getBody();
		System.out.println("ID******************: " + claims.getSubject());
		System.out.println("Id is varified :" + claims.getSubject());

		return Integer.parseInt(claims.getSubject());
	}

	@Override
	public String jwtToken(String secretKey, int id) {
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		JwtBuilder builder = Jwts.builder().setSubject(String.valueOf(id)).setIssuedAt(now)
				.signWith(SignatureAlgorithm.HS256, "base64SecretBytes");
		System.out.println("jwt token :" + builder.compact());
		String token = builder.compact();

		return token;
	}


}
	