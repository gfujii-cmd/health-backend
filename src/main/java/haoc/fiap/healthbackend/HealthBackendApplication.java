package haoc.fiap.healthbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HealthBackendApplication {

	@Bean
	public static void main(String[] args) {
		SpringApplication.run(HealthBackendApplication.class, args);
	}

}
