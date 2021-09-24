package com.example.boiler.common;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ResponseDto<T> {

  public static final ResponseDto<?> OK = ResponseDto.builder().build();

  @Builder.Default
  private final int statusCode = HttpStatus.OK.value();
  @Builder.Default
  private final String message = HttpStatus.OK.getReasonPhrase();
  private final T result;

  public static <T> ResponseDto<?> res(HttpStatus status, T result) {
    return ResponseDto.builder()
        .statusCode(status.value())
        .message(status.getReasonPhrase())
        .result(result)
        .build();
  }
}
