package GustoGroup.BcryptEncoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//Can encode password a print it out
public class BcryptEncoderTest {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedString = encoder.encode("gusto1802");
		System.out.println(encodedString);
	}
}
