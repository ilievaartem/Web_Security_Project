package com.ilievaartem.gamemarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class GameMarketplaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameMarketplaceApplication.class, args);
	}

}
