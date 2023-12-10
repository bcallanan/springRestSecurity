package com.bcallanan.myBank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
//@EnableJpaRepositories( "com.bcallanan.myBank.jpa" )
//@EntityScan( "com.bcallanan.myBank.entity")
@EnableWebSecurity(debug=true)
public class MyBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBankApplication.class, args);
	}

}
