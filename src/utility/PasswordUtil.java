package utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
	public static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public static String encode(String password) {
		return encoder.encode(password);
	}
	public static boolean matches(String password, String hashedPassword) {
		return encoder.matches(password, hashedPassword);
	}
}
