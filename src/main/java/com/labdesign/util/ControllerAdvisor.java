package com.labdesign.util;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.labdesign.pojo.ErrorResponse;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
		ErrorResponse res = new ErrorResponse(ExceptionUtils.getRootCause(ex).getClass().getName(),ExceptionUtils.getRootCauseMessage(ex)); 
		return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
