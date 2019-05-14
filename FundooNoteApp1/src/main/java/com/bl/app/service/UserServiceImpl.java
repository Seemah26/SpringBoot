package com.bl.app.service;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bl.app.model.UserInfo;
import com.bl.app.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	public UserRepository userRep;
	
	@Autowired
	private JavaMailSender sender;
	
	String secretKey;
	String subject;

	@Override
	public String login(UserInfo user) {
		String password = encryptedPassword(user);
		List<UserInfo> userList = userRep.findByIdAndPassword(user.getId(), password);
		System.out.println("idddd :" + user.getId());
		System.out.println("SIZE : " + userList.size());

		if (userList.size() > 0 && userList != null) {
			System.out.println("Sucessful login");
			return jwtToken(password, userList.get(0).getId());
		} else
			System.out.println("wrong Id or password");
		return "wrong id or password";
	}

	@Override
	public UserInfo update(String token, UserInfo user) {
		int varifiedUserId = tokenVerification(token);

		Optional<UserInfo> maybeUser = userRep.findById(varifiedUserId);
		UserInfo presentUser = maybeUser.map(existingUser -> {
			existingUser.setEmail(user.getEmail() != null ? user.getEmail() : maybeUser.get().getEmail());
			existingUser.setPhonenumber(
					user.getPhonenumber() != null ? user.getPhonenumber() : maybeUser.get().getPhonenumber());
			existingUser.setName(user.getName() != null ? user.getName() : maybeUser.get().getName());
			existingUser
					.setPassword(user.getPassword() != null ? encryptedPassword(user) : maybeUser.get().getPassword());
			return existingUser;
		}).orElseThrow(() -> new RuntimeException("User Not Found"));

		return userRep.save(presentUser);
	}

	@Override
	public boolean delete(String token) {
		int varifiedUserId = tokenVerification(token);
		Optional<UserInfo> maybeUser = userRep.findById(varifiedUserId);
		return maybeUser.map(existingUser -> {
			userRep.delete(existingUser);
			return true;
		}).orElseGet(() -> false);
	}

	@Override
	public UserInfo userRegistration(UserInfo user) {
		user.setPassword(encryptedPassword(user));
		return userRep.save(user);
	}

	@Override
	public String encryptedPassword(UserInfo user) {
		String passwordToHash = user.getPassword();
		System.out.println("password: " + passwordToHash);
		String generatedPassword = null;
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			// Add password bytes to digest
			md.update(passwordToHash.getBytes());
			// Get the hash's bytes
			byte[] bytes = md.digest();
			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		System.out.println("generated password :" + generatedPassword);

		return generatedPassword;

	}

	private static final Key secret = MacProvider.generateKey(SignatureAlgorithm.HS256);
	private static final byte[] secretBytes = secret.getEncoded();
	private static final String base64SecretBytes = Base64.getEncoder().encodeToString(secretBytes);

	@Override
	public String jwtToken(String secretKey, int id) {
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		JwtBuilder builder = Jwts.builder().setSubject(String.valueOf(id)).setIssuedAt(now)
				.signWith(SignatureAlgorithm.HS256, base64SecretBytes);
		System.out.println("jwt token :" + builder.compact());
		String token = builder.compact();

		return token;
	}

	@Override
	public int tokenVerification(String token) {
		// This line will throw an exception if it is not a signed JWS (as expected)
		if (StringUtils.isEmpty(token)) {
		}
		Claims claims = Jwts.parser().setSigningKey(base64SecretBytes).parseClaimsJws(token).getBody();
		System.out.println("ID******************: " + claims.getSubject());
		System.out.println("Id is varified :" + claims.getSubject());

		return Integer.parseInt(claims.getSubject());
	}

	@Override
	public UserInfo getUserInfoByEmail(String email) {
		return userRep.findByEmail(email);
		
						
		
	}
	
	public  String sendMail(UserInfo user,HttpServletRequest request,String token) {
		
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		
		StringBuffer requestUrl = request.getRequestURL();
		System.out.println(requestUrl);
		String forgotPasswordUrl = requestUrl.substring(0, requestUrl.lastIndexOf("/"));
		forgotPasswordUrl = forgotPasswordUrl + "/resetpassword/" +"token="+ token;
		System.out.println(forgotPasswordUrl);

		try {
			helper.setTo(user.getEmail());
			helper.setText(forgotPasswordUrl);
			helper.setSubject("Token Based Auth");
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Error while sending mail ..";
		}
		sender.send(message);
		return "Mail Sent Success!";
		
			
	}

	public Optional<UserInfo> findById(int id) {
		return userRep.findById(id);
		
	}

}