package com.example.boiler.domain.member.service;

import com.example.boiler.domain.member.dto.MemberDto;
import com.example.boiler.domain.member.model.entity.Member;
import com.example.boiler.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  public void save(MemberDto memberDto) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    memberDto.setPassword(encoder.encode(memberDto.getPassword()));
    Member m = memberRepository.save(memberDto.toEntity());
    log.info("member = {}", m);
  }
}
