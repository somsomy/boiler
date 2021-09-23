package com.example.boiler.domain.member.model.entity;

import com.example.boiler.domain.member.dto.MemberDto;
import com.example.boiler.domain.member.model.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Member extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MEMBER_ID")
  private Long id;
  @NotNull
  @Column(unique = true)
  private String userId;
  private String password;
  @NotNull
  @Column(unique = true)
  private String name;
  @NotNull
  @Enumerated(EnumType.STRING)
  private Role role;

  public MemberDto toDto() {
    return MemberDto.builder()
        .memberId(id)
        .userId(userId)
        .password(password)
        .name(name)
        .role(role)
        .build();
  }
}
