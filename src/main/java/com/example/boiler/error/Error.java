package com.example.boiler.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
@AllArgsConstructor
public enum Error {
  FORBIDDEN(HttpStatus.FORBIDDEN, "Forbidden!!!!!!");

  private final HttpStatus status;
  private final String message;
}
