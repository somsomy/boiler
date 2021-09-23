package com.example.boiler.domain.member.dto;

import com.example.boiler.domain.member.model.Role;
import com.example.boiler.domain.member.model.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
public class MemberDto {
  private Long memberId;
  private String userId;
  private String password;
  private String name;
  private Role role;

  public Member toEntity() {
    return Member.builder()
        .id(memberId)
        .userId(userId)
        .password(password)
        .name(name)
        .role(role)
        .build();
  }
}
