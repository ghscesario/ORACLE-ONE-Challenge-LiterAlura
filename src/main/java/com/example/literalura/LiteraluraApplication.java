package com.example.literalura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.literalura.Principal.Principal;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner{

	@Autowired
	Principal p = new Principal();

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		p.menu();
	}

}
