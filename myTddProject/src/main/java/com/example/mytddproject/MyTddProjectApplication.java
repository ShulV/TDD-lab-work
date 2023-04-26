package com.example.mytddproject;

import com.example.mytddproject.ui.CurrencyServiceInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MyTddProjectApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(MyTddProjectApplication.class, args);

		CurrencyServiceInterface currencyServiceInterface = new CurrencyServiceInterface();

		currencyServiceInterface.run();

		context.close();
	}

}
