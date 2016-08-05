import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncodingTest {

	@Test
	public void test() {
		PasswordEncoder enc = new BCryptPasswordEncoder(10);
		System.out.println(enc.encode("admin"));
	}

}
