package com.example.boiler.domain.member.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginDto {
  private String userId;
  private String password;
}
