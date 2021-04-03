package com.idontchop.dateprofileservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Notes:
 * 
 * Not Secured via JWT. Endpoints exist that allow modification of database without auth
 * 
 * @author nathan
 *
 */
@SpringBootApplication
public class DateProfileServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DateProfileServiceApplication.class, args);
	}

}
