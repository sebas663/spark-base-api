/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
package app.token;

import java.util.Base64;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import spark.Request;
/**
 * 
 * @author Sebas663
 *
 */
public class TokenProviderJwt implements TokenProvider {

	private static TokenProviderJwt instance;
	private TokenProperties jwtProperties;

	public TokenProviderJwt() {
		super();
		init();
	}

	public static synchronized TokenProviderJwt getInstance() {
		if (instance == null) {
			synchronized (TokenProviderJwt.class) {
				if (instance == null) {
					instance = new TokenProviderJwt();
				}
			}
		}
		return instance;
	}

	private String secretKey;

	protected void init() {
		jwtProperties = new TokenProperties();
		secretKey = Base64.getEncoder().encodeToString(jwtProperties.getSecretKey().getBytes());
	}

	@Override
	public String createToken(String username) {

		Claims claims = Jwts.claims().setSubject(username);

		Date now = new Date();
		Date validity = new Date(now.getTime() + jwtProperties.getValidityInMs());

		return Jwts.builder()//
				.setClaims(claims)//
				.setIssuedAt(now)//
				.setExpiration(validity)//
				.signWith(SignatureAlgorithm.HS256, secretKey)//
				.compact();
	}

	@Override
	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	@Override
	public String resolveToken(Request req) {
		String bearerToken = req.headers("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

	@Override
	public boolean validateToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

			if (claims.getBody().getExpiration().before(new Date())) {
				return false;
			}

			return true;
		} catch (JwtException | IllegalArgumentException e) {
			throw new IllegalArgumentException("Expired or invalid JWT token");
		}
	}
}
