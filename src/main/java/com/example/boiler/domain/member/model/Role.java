package com.example.boiler.domain.member.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
  ADMIN("ROLE_ADMIN"),
  USER("ROLE_USER");

  private String value;
}
