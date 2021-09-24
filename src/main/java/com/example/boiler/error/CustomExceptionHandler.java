package com.example.boiler.error;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class CustomExceptionHandler {
  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ErrorResponse> accessDeniedExceptionHandler(AccessDeniedException e) {
    return ResponseEntity.status(Error.FORBIDDEN.getStatus())
        .body(ErrorResponse.builder()
            .status(Error.FORBIDDEN.getStatus())
            .message(Error.FORBIDDEN.getMessage())
            .build());
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<ErrorResponse> usernameNotFoundException(HttpServletRequest request, UsernameNotFoundException e) {
    return ResponseEntity.status(Error.FORBIDDEN.getStatus())
        .body(ErrorResponse.builder()
            .status(Error.FORBIDDEN.getStatus())
            .message(Error.FORBIDDEN.getMessage())
            .build());
  }
}
