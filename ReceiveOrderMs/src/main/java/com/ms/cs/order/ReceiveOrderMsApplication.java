package com.ms.cs.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author w2cluster03
 *
 */
@SpringBootApplication
@EnableSwagger2
public class ReceiveOrderMsApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ReceiveOrderMsApplication.class, args);
	}

}
