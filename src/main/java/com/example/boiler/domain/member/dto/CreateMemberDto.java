package com.example.boiler.domain.member.dto;

import com.example.boiler.domain.member.model.entity.Member;
import lombok.Data;

@Data
public class CreateMemberDto {
  private String userId;
  private String password;
  private String name;

  public Member toEntity() {
    return Member.builder()
        .userId(userId)
        .password(password)
        .name(name)
        .build();
  }
}
