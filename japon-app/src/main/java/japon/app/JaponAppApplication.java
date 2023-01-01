package japon.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"japon"})
public class JaponAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(JaponAppApplication.class, args);
	}

}
