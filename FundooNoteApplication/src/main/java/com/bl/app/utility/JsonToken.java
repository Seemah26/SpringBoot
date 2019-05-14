package com.bl.app.utility;

public interface JsonToken {
	public int tokenVerification(String token);
	public String jwtToken(String secretKey, int id);
}
