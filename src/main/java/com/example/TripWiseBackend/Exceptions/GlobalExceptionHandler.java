package com.example.TripWiseBackend.Exceptions;

import com.example.TripWiseBackend.Exceptions.CustomExceptions.CustomGenericException;
import com.example.TripWiseBackend.Exceptions.CustomExceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map> handleResourceNotFoundException(ResourceNotFoundException e) {
        Map response = new HashMap();
        response.put("message",e.getMessage());
        return new ResponseEntity(response,HttpStatusCode.valueOf(409));
    }

    @ExceptionHandler(CustomGenericException.class)
    public ResponseEntity<Map> handleCustomGenericException(CustomGenericException e) {
        Map response = new HashMap();
        response.put("message",e.getMessage());
        return new ResponseEntity(response,HttpStatusCode.valueOf(409));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map> handleBadCredentialsException(BadCredentialsException e) {
        Map response = new HashMap();
        response.put("message",e.getMessage());
        return new ResponseEntity(response,HttpStatusCode.valueOf(409));
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Map> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        Map response = new HashMap();
        response.put("message",e.getMessage());
        return new ResponseEntity(response,HttpStatusCode.valueOf(409));
    }

}
