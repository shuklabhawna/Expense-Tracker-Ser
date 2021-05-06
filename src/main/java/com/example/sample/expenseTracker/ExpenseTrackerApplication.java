package com.example.sample.expenseTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.example.sample.expenseTracker.controller.GlobalControllerExceptionHandler;


@SpringBootApplication
@Import(GlobalControllerExceptionHandler.class)
public class ExpenseTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackerApplication.class, args);
	}

}
