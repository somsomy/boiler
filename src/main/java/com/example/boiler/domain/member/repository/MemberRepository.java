package com.example.boiler.domain.member.repository;

import com.example.boiler.domain.member.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
  Optional<Member> findByUserId(String userId);
}
