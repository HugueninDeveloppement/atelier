package fr.epsi.rennes.poec.vanessa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "fr.epsi.rennes.poec.vanessa")
public class ApplicationAtelier {
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationAtelier.class, args);
	}
}