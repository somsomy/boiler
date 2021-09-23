package com.example.boiler.security;

import com.example.boiler.domain.member.model.entity.Member;
import com.example.boiler.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("=== loadUserByUsername ===");

    Member member = memberRepository.findByUserId(username)
        .orElseThrow(() -> {
          log.error("=== User NotFoundException ===");
          return new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        });
    log.info("load member = {}", member);

    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    authorities.add(new SimpleGrantedAuthority(member.getRole().name()));
    authorities.add(new SimpleGrantedAuthority(member.getRole().getValue()));

    return new CustomUserDetails(
        member.getId(),
        member.getUserId(),
        member.getName(),
        member.getPassword(),
        member.getRole(),
        authorities
    );
  }
}
