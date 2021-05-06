
package com.example.sample.expenseTracker.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.sample.expenseTracker.exception.ApplicationException;
import com.example.sample.expenseTracker.exception.UnauthorizedException;
import com.example.sample.expenseTracker.web.ErrorResponse;


@ControllerAdvice
public class GlobalControllerExceptionHandler{
	private final Logger logger = LoggerFactory
            .getLogger(GlobalControllerExceptionHandler.class);

	 @ExceptionHandler(UnauthorizedException.class)
	    public ResponseEntity<ErrorResponse> handleUnauthorizedException(
	            UnauthorizedException ex) {
	        logger.info("Unauthorized exception", ex);

	        ErrorResponse errorResponse = new ErrorResponse();
	        errorResponse.setErrorMessage("You are not authorized to perform this action. Please contact the system administrator.");
	        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
	    }


	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
	            MethodArgumentNotValidException ex) {
	        logger.info("Method argument not valid exception", ex);

	        Map<String, Object> errorMap = new HashMap<>();
	        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
	            if (fieldError.getRejectedValue() == null) {
	                errorMap.put(fieldError.getField(),
	                        fieldError.getDefaultMessage());
	            } else {
	                errorMap.put(
	                        fieldError.getField(),
	                        fieldError.getDefaultMessage() + " ["
	                                + fieldError.getRejectedValue() + "]");
	            }
	        }

	        ErrorResponse errorResponse = new ErrorResponse();
	        errorResponse.setErrorFields(errorMap);
	        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	    }

	    @ExceptionHandler(ValidationException.class)
	    public ResponseEntity<ErrorResponse> handleValidationException(
	            ValidationException ex) {
	        logger.info("Validation exception", ex);

	        ErrorResponse errorResponse = new ErrorResponse();
	        errorResponse.setErrorMessage(ex.getMessage());

	        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	    }

	    @ExceptionHandler(DataIntegrityViolationException.class)
	    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(
	            DataIntegrityViolationException ex) {
	        logger.info("DataIntegrityViolationException exception", ex);

	        ErrorResponse errorResponse = new ErrorResponse();
	        errorResponse.setErrorMessage("Cannot save/update duplicate record.");

	        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	    }

	    @ExceptionHandler(OptimisticLockingFailureException.class)
	    public ResponseEntity<ErrorResponse> handleOptimisticLockingFailureException(
	            OptimisticLockingFailureException ex) {
	        logger.info("OptimisticLockingFailureException exception", ex);

	        ErrorResponse errorResponse = new ErrorResponse();
	        errorResponse.setErrorMessage("Optimistic locking failed.");

	        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	    }

	    @ExceptionHandler(HttpMessageNotReadableException.class)
	    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
	            HttpMessageNotReadableException ex) {
	        logger.info("HttpMessageNotReadableException exception", ex);

	        ErrorResponse errorResponse = new ErrorResponse();
	        errorResponse
	                .setErrorMessage("Unrecognized or incorrect message content.");

	        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	    }

	    @ExceptionHandler(MissingServletRequestParameterException.class)
	    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(
	            MissingServletRequestParameterException ex) {
	        logger.info("MissingServletRequestParameterException exception", ex);

	        ErrorResponse errorResponse = new ErrorResponse();
	        errorResponse.setErrorMessage(ex.getMessage());

	        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	    }

	    @ExceptionHandler(RuntimeException.class)
	    public ResponseEntity<ErrorResponse> handleRuntimeException(
	            RuntimeException ex) {
	        UUID errorCode = UUID.randomUUID();
	        logger.error("RuntimeException Error Code: [{}]", errorCode);
	        logger.error("RuntimeException", ex);

	        ErrorResponse errorResponse = new ErrorResponse();
	        errorResponse.setErrorMessage("System error occurred.");
	        errorResponse.setErrorCode(errorCode.toString());
	        return new ResponseEntity<>(errorResponse,
	                HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
	        UUID errorCode = UUID.randomUUID();
	        logger.error("Exception Error Code: [{}]", errorCode);
	        logger.error("Exception", ex);

	        ErrorResponse errorResponse = new ErrorResponse();
	        errorResponse.setErrorMessage("System error occurred.");
	        errorResponse.setErrorCode(errorCode.toString());
	        return new ResponseEntity<>(errorResponse,
	                HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	    @ExceptionHandler(ApplicationException.class)
	    public ResponseEntity<ErrorResponse> handleAGCBaseException(
	            ApplicationException ex) {

	        logger.error("ApplicationException", ex);

	        ErrorResponse errorResponse = new ErrorResponse();
	        errorResponse.setErrorMessage(ex.getMessage());
	        return new ResponseEntity<>(errorResponse,
	                HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}