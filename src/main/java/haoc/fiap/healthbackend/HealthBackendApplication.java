package haoc.fiap.healthbackend;

import haoc.fiap.healthbackend.entity.Job;
import haoc.fiap.healthbackend.entity.User;
import haoc.fiap.healthbackend.entity.WashMachine;
import haoc.fiap.healthbackend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAutoConfiguration
public class HealthBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthBackendApplication.class, args);
	}

}
