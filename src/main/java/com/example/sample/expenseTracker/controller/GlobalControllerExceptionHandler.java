package com.example.sample.expenseTracker.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;abstract

@ControllerAdvice
public class GlobalControllerExceptionHandler {
	private final Logger logger = LoggerFactory
            .getLogger(GlobalControllerExceptionHandler.class);

}
