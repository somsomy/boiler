package com.example.boiler.domain.member.model.entity;

import com.example.boiler.domain.member.model.Role;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@ToString
@NoArgsConstructor
@Getter
@DynamicInsert
public class Member extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MEMBER_ID")
  private Long id;
  @NotNull
  @Column(unique = true)
  private String userId;
  @NotNull
  private String password;
  @NotNull
  @Column(unique = true)
  private String name;
  @Column(nullable = false) @ColumnDefault("'USER'")
  @Enumerated(EnumType.STRING)
  private Role role;

  @Builder
  Member(LocalDateTime createAt, LocalDateTime updateAt,
         Long id, String userId, String password, String name, Role role) {
    super(createAt, updateAt);
    this.id = id;
    this.userId = userId;
    this.password = password;
    this.name = name;
    this.role = role;
  }
}
